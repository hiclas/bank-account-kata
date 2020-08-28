package com.bank.account.services;

import java.util.List;

import com.bank.account.dtos.TransactionDTO;
import com.bank.account.dtos.TransactionDetailsDto;

public interface TransactionService {

	/**
	 * The createTransaction service: this service manages the operations: deposit
	 * and withdrawal.
	 * 
	 * @param idAccount       the identifier of the connected account.
	 * @param transactionBean The transaction details
	 * @return
	 */
	public TransactionDTO createTransaction(Long idAccount, TransactionDetailsDto transactionBean);

}
