package com.bank.account.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bank.account.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The transaction entity details.
 *
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	/** The transaction identifier. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** The transaction type. */
	@Column(nullable = false)
	private TransactionType transactionType;
	/** The amount of the transaction. */
	@Column(nullable = false)
	private Double amount;
	/** The motive of the transaction. */
	@Column(nullable = true)
	private String motive;
	/** The date of the transaction*/
	@Builder.Default
	@Column(nullable = false)
	private Date date = new Date();

	/**
	 * The transaction emitter account.
	 */
	@ManyToOne
	@JoinColumn(nullable = true)
	private Account account;

}
