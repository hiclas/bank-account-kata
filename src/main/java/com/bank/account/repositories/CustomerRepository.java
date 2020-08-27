package com.bank.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.account.entities.Customer;

/**
 * The customer JPA repository.
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
