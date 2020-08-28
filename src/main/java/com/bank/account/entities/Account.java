package com.bank.account.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Account entity.
 *
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	/** Account identifier. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Account number. */
	@Column(length = 11, nullable = false)
	private Long accountNumber;

	/** Account balance (zero by default). */
	@Builder.Default
	@Column(nullable = false)
	private Double balance = 0.0;


	/** The owner of the account (a customer can have multiple accounts).*/
	@ManyToOne
	@JoinColumn(nullable = false)
	private Customer customer;

}
