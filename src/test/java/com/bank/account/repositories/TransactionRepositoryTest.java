package com.bank.account.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.bank.account.entities.Account;
import com.bank.account.entities.Bank;
import com.bank.account.entities.Customer;
import com.bank.account.entities.Transaction;
import com.bank.account.enums.Country;
import com.bank.account.enums.TransactionType;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DataJpaTest
public class TransactionRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TransactionRepository transactionRepository;

	@DisplayName("When FindAllByAccountId then Return Transactions")
	@Test
	public void whenFindAllByAccountId_thenReturnTransactions() {

		final Bank bank = Bank.builder()
				.bankName("ING")
				.bankAccountNumber("INGXXXXX")
				.build();
		entityManager.persist(bank);

		final Customer firstCustomer = Customer.builder()
				.firstName("HIC")
				.lastName("LASFAR")
				.email("HICLAS@GMAIL.COM")
				.address("52 RUE DES PIERRELAIS")
				.zipCode("92320")
				.city("Chatillon")
				.country(Country.FRANCE)
				.build();
		entityManager.persist(firstCustomer);

		final Customer secondCustomer = Customer.builder()
				.firstName("NADA")
				.lastName("LASFAR")
				.email("NADLAS@GMAIL.COM")
				.address("52 RUE DES PIERRELAIS")
				.zipCode("92320")
				.city("Chatillon")
				.country(Country.FRANCE)
				.build();
		entityManager.persist(secondCustomer);

		final Account firstAccount = Account.builder()
				.accountNumber(50553980L)
				.balance(1000.0)
				.customer(firstCustomer)
				.build();
		entityManager.persist(firstAccount);

		final Account secondAccount = Account.builder()
				.accountNumber(50553981L)
				.balance(1000.0)
				.customer(secondCustomer)
				.build();
		entityManager.persist(secondAccount);

		final Transaction firstTransaction = Transaction.builder()
				.transactionType(TransactionType.DEPOSIT_OPERATION)
				.amount(1000.0)
				.motive(null)
				.date(new Date())
				.account(firstAccount)
				.build();
		final Transaction secondTransaction = Transaction.builder()
				.transactionType(TransactionType.DEPOSIT_OPERATION)
				.amount(100.0)
				.motive(null)
				.date(new Date())
				.account(firstAccount)
				.build();
		final Transaction thirdTransaction = Transaction.builder()
				.transactionType(TransactionType.WITHDRAWAL_OPERATION)
				.amount(100.0)
				.motive(null)
				.date(new Date())
				.account(firstAccount)
				.build();
		entityManager.persist(firstTransaction);
		entityManager.persist(secondTransaction);
		entityManager.persist(thirdTransaction);
		entityManager.flush();

		List<Transaction> result = transactionRepository.findAllByAccountId(firstAccount.getId());

		assertThat(result).hasSize(3);

	}

}
