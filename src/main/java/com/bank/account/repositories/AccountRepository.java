package com.bank.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.account.entities.Account;

/**
 * The account JPA repository.
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
