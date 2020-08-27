package com.bank.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.account.entities.Transaction;

/**
 * The transaction JPA repository.
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
