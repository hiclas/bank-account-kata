package com.bank.account.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.account.entities.Transaction;

/**
 * The transaction JPA repository.
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query("SELECT transaction FROM Transaction transaction WHERE account.id = :accountId")
	public List<Transaction> findAllByAccountId(@Param("accountId") Long accountId);
}
