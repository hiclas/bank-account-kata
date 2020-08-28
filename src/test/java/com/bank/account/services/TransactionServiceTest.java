package com.bank.account.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;

import com.bank.account.dtos.TransactionDTO;
import com.bank.account.dtos.TransactionDetailsDto;
import com.bank.account.entities.Account;
import com.bank.account.entities.Customer;
import com.bank.account.entities.Transaction;
import com.bank.account.enums.Country;
import com.bank.account.enums.TransactionType;
import com.bank.account.exceptions.AccountNotFoundException;
import com.bank.account.exceptions.AmountNotAllowedException;
import com.bank.account.repositories.AccountRepository;
import com.bank.account.repositories.TransactionRepository;
import com.bank.account.services.impl.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransactionServiceTest {

	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;

	private ModelMapper modelMapper;

	@Mock
	private ModelMapper modelServiceMapper;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private TransactionRepository transactionRepository;

	private Account firstAccount, secondAccount;

	@BeforeEach
	public void init() {
	    MockitoAnnotations.initMocks(this);

		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		final Customer firstCustomer = Customer.builder().id(1L).firstName("HICHAM").lastName("LASFAR")
				.email("hicham.lasfar@GMAIL.COM").address("52 RUE DES PIERRELAIS &;;!").zipCode("92320")
				.city("Chatillon").country(Country.FRANCE).build();

		final Customer secondCustomer = Customer.builder().id(2L).firstName("NADA").lastName("LASFAR")
				.email("nada.lasfar@GMAIL.COM").address("52 RUE DES PIERRELAIS &;;!").zipCode("92320").city("Chatillon")
				.country(Country.FRANCE).build();

		firstAccount = Account.builder().id(1L).accountNumber(50553980L).balance(1000.0).customer(firstCustomer)
				.build();

		secondAccount = Account.builder().id(2L).accountNumber(50553981L).balance(500.0).customer(secondCustomer)
				.build();
		// Mocking findById method of account Repository for parameters 1 and 2
		when(accountRepository.findById(1L)).thenReturn(Optional.of(firstAccount));

		when(accountRepository.findById(2L)).thenReturn(Optional.of(secondAccount));
	}

	/**
	 * Valid deposit operation test.
	 * 
	 * @throws Exception exception (this test should not throw an exception)
	 */
	@DisplayName("Deposit Valid Amount in Account")
	@ParameterizedTest
	@ValueSource(doubles = { 0.01, 100, 300, 369, Double.MAX_VALUE })
	public void testDepositValidAmountInAccount(Double amount) throws Exception {

		// Construction of the transaction details bean
		final TransactionDetailsDto transactionBean = new TransactionDetailsDto();
		transactionBean.setTransactionType(TransactionType.DEPOSIT_OPERATION);
		transactionBean.setAmount(amount);
		transactionBean.setMotive(null);
		transactionBean.setDate(new Date());
		transactionBean.setAccountId(null);

		final Account firstAccountAmountUpdated = Account.builder().build();
		BeanUtils.copyProperties(firstAccount, firstAccountAmountUpdated);
		firstAccountAmountUpdated.setBalance(firstAccount.getBalance() + amount);

		// When calling save on the first account we should return this account updated
		when(accountRepository.save(firstAccountAmountUpdated)).thenReturn(firstAccountAmountUpdated);

		final Transaction transaction = Transaction.builder().id(1L)
				.transactionType(transactionBean.getTransactionType()).amount(transactionBean.getAmount())
				.motive(transactionBean.getMotive()).date(transactionBean.getDate()).account(firstAccountAmountUpdated)
				.build();

		when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

		final TransactionDTO transactionDTOExpected = modelMapper.map(transaction, TransactionDTO.class);

		when(modelServiceMapper.map(transaction, TransactionDTO.class)).thenReturn(transactionDTOExpected);

		transactionServiceImpl.setMinDepositAmount(0.01D);

		final TransactionDTO transactionDTOResult = transactionServiceImpl.createTransaction(1L, transactionBean);

		assertEquals(transactionDTOExpected, transactionDTOResult);

		verify(accountRepository, times(1)).findById(1L);
		verify(accountRepository, times(1)).save(firstAccountAmountUpdated);
		verify(transactionRepository, times(1)).save(any(Transaction.class));

	}

	/**
	 * The deposit invalid test case.
	 * 
	 * @param invalidAmount should be less than: 0.01
	 * @throws Exception
	 */
	@DisplayName("Deposit Invalid Amount In Account")
	@ValueSource(doubles = { 0.009, 0, -44 })
	@ParameterizedTest
	public void testDepositInvalidAmountInAccount(Double invalidAmount) throws Exception {
		assertThrows(AmountNotAllowedException.class, () -> {
			final TransactionDetailsDto transactionBean = new TransactionDetailsDto();
			transactionBean.setTransactionType(TransactionType.DEPOSIT_OPERATION);
			transactionBean.setAmount(invalidAmount);
			transactionBean.setMotive(null);
			transactionBean.setDate(new Date());
			transactionBean.setAccountId(1L);

			firstAccount.setBalance(0.0);
			transactionServiceImpl.setMinDepositAmount(0.01D);
			transactionServiceImpl.createTransaction(1L, transactionBean);
		});

	}
	/**
	 * The withdrawal valid test case.
	 * 
	 * @param withdrawalAmount must be less or equal than the account balance
	 * @throws Exception
	 */
	@DisplayName("Withdraw Valid Amount From Account")
	@ValueSource(doubles = { 0.1, 55 })
	@ParameterizedTest
	public void testWithdrawValidAmountFromAccount(Double withdrawalAmount) throws Exception {

		final TransactionDetailsDto transactionBean = new TransactionDetailsDto();
		transactionBean.setTransactionType(TransactionType.WITHDRAWAL_OPERATION);
		transactionBean.setAmount(100.0);
		transactionBean.setMotive(null);
		transactionBean.setDate(new Date());
		transactionBean.setAccountId(1L);

		final Account firstAccountBalanceUpdated = Account.builder().build();
		BeanUtils.copyProperties(firstAccount, firstAccountBalanceUpdated);
		firstAccountBalanceUpdated.setBalance(firstAccount.getBalance() - 100.0);

		when(accountRepository.save(firstAccountBalanceUpdated)).thenReturn(firstAccountBalanceUpdated);

		final Transaction transaction = Transaction.builder().id(1L)
				.transactionType(transactionBean.getTransactionType()).amount(transactionBean.getAmount())
				.motive(transactionBean.getMotive()).date(transactionBean.getDate()).account(firstAccountBalanceUpdated)
				.build();

		when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

		final TransactionDTO transactionDTOExpected = modelMapper.map(transaction, TransactionDTO.class);

		when(modelServiceMapper.map(transaction, TransactionDTO.class)).thenReturn(transactionDTOExpected);
		transactionServiceImpl.setMinDepositAmount(0.01D);
		final TransactionDTO transactionDTOResult = transactionServiceImpl.createTransaction(1L, transactionBean);

		assertEquals(transactionDTOExpected, transactionDTOResult);

		verify(accountRepository, times(1)).findById(1L);
		verify(accountRepository, times(1)).save(firstAccountBalanceUpdated);
		verify(transactionRepository, times(1)).save(any(Transaction.class));

	}

	/**
	 * Test with invalid amount.
	 * 
	 * @param invalidAmount (should be less or equal zero or greater than the
	 *                      account balance)
	 * @throws Exception
	 */
	@DisplayName("Withdraw Invalid Amount From Account")
	@ValueSource(doubles = { 0, -0.01, 55000 })
	@ParameterizedTest
	public void testWithdrawInvalidAmountFromAccount(Double invalidAmount) throws Exception {

		assertThrows(AmountNotAllowedException.class, () -> {
			final TransactionDetailsDto transactionBean = new TransactionDetailsDto();
			transactionBean.setTransactionType(TransactionType.WITHDRAWAL_OPERATION);
			transactionBean.setAmount(invalidAmount);
			transactionBean.setMotive(null);
			transactionBean.setDate(new Date());
			transactionBean.setAccountId(1L);
			transactionServiceImpl.setMinDepositAmount(0.01D);
			transactionServiceImpl.createTransaction(1L, transactionBean);
		});

	}
	/**
	 * Test of read balance account for an existing account.
	 * 
	 * @throws Exception
	 */
	@DisplayName("Get account balance for an existing account")
	@Test
	public void testReadAccountBalanceValidAccount() throws Exception {
		assertEquals(firstAccount.getBalance(), transactionServiceImpl.readAccountBalance(firstAccount.getId()));
		assertEquals(secondAccount.getBalance(), transactionServiceImpl.readAccountBalance(secondAccount.getId()));
	}

	/**
	 * Test of read account balance for an invalid account (expected
	 * AccountNotFoundException).
	 * 
	 * @throws Exception
	 */
	@DisplayName("Get account balance for an invalid account")
	@Test
	public void testReadAccountBalanceInvalidAccount() throws Exception {
		assertThrows(AccountNotFoundException.class, () -> {
			// 4L is a none existing account
			transactionServiceImpl.readAccountBalance(4L);
		});
	}

	/**
	 * Test of get bank account transaction history for an existing account
	 * 
	 * @throws Exception
	 */
	@DisplayName("Get Bank Account Transaction History")
	@Test
	public void testGetBankAccountTransactionHistoryForValidAccount() throws Exception {

		List<Transaction> transactions = Arrays.asList(
				Transaction.builder().id(1L).transactionType(TransactionType.DEPOSIT_OPERATION).amount(1000.0)
						.motive("First Deposit").date(new Date()).account(firstAccount).build(),
				Transaction.builder().id(1L).transactionType(TransactionType.DEPOSIT_OPERATION).amount(100.0)
						.motive(null).date(new Date()).account(firstAccount).build(),
				Transaction.builder().id(1L).transactionType(TransactionType.WITHDRAWAL_OPERATION).amount(100.0)
						.motive(null).date(new Date()).account(firstAccount).build());

		when(transactionRepository.findAllByAccountId(1L)).thenReturn(transactions);

		List<TransactionDTO> transactionDTOs = transactions.stream()
				.map(transaction -> modelMapper.map(transaction, TransactionDTO.class)).collect(Collectors.toList());

		when(modelServiceMapper.map(transactions.get(0), TransactionDTO.class))
				.thenReturn(modelMapper.map(transactions.get(0), TransactionDTO.class));
		when(modelServiceMapper.map(transactions.get(1), TransactionDTO.class))
				.thenReturn(modelMapper.map(transactions.get(1), TransactionDTO.class));
		when(modelServiceMapper.map(transactions.get(2), TransactionDTO.class))
				.thenReturn(modelMapper.map(transactions.get(2), TransactionDTO.class));

		List<TransactionDTO> result = transactionServiceImpl.getBankAccountTransactionHistory(1L);

		assertEquals(transactionDTOs, result);

		verify(transactionRepository, times(1)).findAllByAccountId(1L);

	}

	/**
	 * Test of get bank account transaction history for an invalid account
	 * 
	 * @throws Exception
	 */
	@DisplayName("Get Bank Account Transaction History")
	@Test
	public void testGetBankAccountTransactionHistoryForInvalidAccount() throws Exception {
		assertThrows(AccountNotFoundException.class, () -> {
			// 4L is a none existing account
			transactionServiceImpl.readAccountBalance(4L);
		});
	}
}
