package com.bank.account.services.impl;


import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.account.dtos.TransactionDTO;
import com.bank.account.dtos.TransactionDetailsDto;
import com.bank.account.entities.Account;
import com.bank.account.entities.Transaction;
import com.bank.account.exceptions.AccountNotFoundException;
import com.bank.account.exceptions.AmountNotAllowedException;
import com.bank.account.exceptions.IllegalParameterException;
import com.bank.account.repositories.AccountRepository;
import com.bank.account.repositories.TransactionRepository;
import com.bank.account.services.TransactionService;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	private Double minDepositAmount;
	
	@Override
	public TransactionDTO createTransaction(Long idAccount, TransactionDetailsDto transactionDetails) {
		if (transactionDetails.getAmount() < minDepositAmount) {
			throw new AmountNotAllowedException();
		}
		final Transaction transaction;

		switch (transactionDetails.getTransactionType()) {
			// Deposit operation
		case DEPOSIT_OPERATION:
			transaction = processDepositOperation(idAccount, transactionDetails);
			break;
		case WITHDRAWAL_OPERATION:
			transaction = processWithdrawalOperation(idAccount, transactionDetails);
			break;
		default:
			throw new IllegalParameterException();
		}
		return modelMapper.map(transaction, TransactionDTO.class);
	}


	@Override
	@Transactional(readOnly = true)
	public Double readAccountBalance(Long idAccount) {
		// If the account does not exist we throw an exception otherwise we get the
		// balance of the account.
		return accountRepository.findById(idAccount).orElseThrow(AccountNotFoundException::new).getBalance();
	}
	
	/**
	 * Injection of the minimum value of deposit from application.properties.
	 * @param minDepositAmount
	 */
	@Value("${min.deposit.amount}")
	public void setMinDepositAmount(Double minDepositAmount) {
		this.minDepositAmount = minDepositAmount;
	}
	
	/**
	 * This method checks the deposit condition(s), find the concerned account,
	 * update it, and create a new transaction for traceability.
	 * 
	 * @param idAccount       The concerned account
	 * @param transactionBean the transaction details.
	 * @return The created transaction
	 */
	private Transaction processDepositOperation(Long idAccount, TransactionDetailsDto transactionBean) {
		// Check deposit condition
		final Account account = accountRepository.findById(idAccount).orElseThrow(AccountNotFoundException::new);
		account.setBalance(account.getBalance() + transactionBean.getAmount());
		final Transaction transaction = Transaction.builder().transactionType(transactionBean.getTransactionType())
				.amount(transactionBean.getAmount()).motive(transactionBean.getMotive()).date(new Date())
				.account(account).build();
		accountRepository.save(account);
		return transactionRepository.save(transaction);

	}
	/**
	 * Process withdrawal operation.
	 *
	 * @param idAccount       the id account
	 * @param transactionBean the transaction request
	 * @return the transaction response
	 */
	private Transaction processWithdrawalOperation(Long idAccount, TransactionDetailsDto transactionBean) {
		final Account account = accountRepository.findById(idAccount).orElseThrow(AccountNotFoundException::new);
		if (isWithdrawalAllowed(account, transactionBean.getAmount())) {
			account.setBalance(account.getBalance() - transactionBean.getAmount());
			final Transaction transaction = Transaction.builder().transactionType(transactionBean.getTransactionType())
					.amount(transactionBean.getAmount()).motive(transactionBean.getMotive())
					.date(new Date()).account(account).build();
			accountRepository.save(account);
			return transactionRepository.save(transaction);
		} else {
			throw new AmountNotAllowedException();
		}
	}

	/**
	 * This method verify if the transaction is allowed (no overdraft is used)
	 * 
	 * @param account
	 * @param withdrawalAmount
	 * @return
	 */
	private boolean isWithdrawalAllowed(Account account, Double withdrawalAmount) {
		return withdrawalAmount != null && withdrawalAmount > 0 && account.getBalance() - withdrawalAmount >= 0;
	}
	
}
