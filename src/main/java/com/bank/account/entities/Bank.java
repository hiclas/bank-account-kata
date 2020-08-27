package com.bank.account.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

/**
 * The bank entity.
 *
 */
@Entity
@Builder
@Data
public class Bank {

	/**
	 * The bank identifier.
	 */
	@Id
	@Column(length = 5)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The bank name.
	 */
	@Column(nullable = false)
	private String bankName;

	/**
	 * The bank account number.	
	 */
	@Column(nullable = false)
	private String bankAccountNumber;

}
