package com.bank.account.dtos;

import java.util.Date;

import com.bank.account.enums.TransactionType;

import lombok.Data;

@Data
public class TransactionDetailsDto {

	/**
	 * The identifier of the transaction.
	 */
	private Long id;

	/**
	 * Transaction Type.
	 */
	private TransactionType transactionType;

	/**
	 * Transaction Amount.
	 */
	private Double amount;

	/**
	 * Transaction motive.
	 */
	private String motive;

	/**
	 * Operation Date.
	 */
	private Date date;

	/**
	 * The transaction emitter's account identifier.
	 */
	private Long accountId;

}
