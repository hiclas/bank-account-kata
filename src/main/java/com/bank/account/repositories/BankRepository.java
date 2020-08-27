package com.bank.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.account.entities.Bank;

/**
 * The Bank JPA repository.
 *
 */
@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

}
