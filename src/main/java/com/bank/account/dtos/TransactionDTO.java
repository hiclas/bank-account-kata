package com.bank.account.dtos;

import java.util.Currency;
import java.util.Date;

import com.bank.account.enums.TransactionType;

import lombok.Data;

/**
 * Transaction DTO.
 */
@Data
public class TransactionDTO {

	/**
	 * Id Transaction.
	 */
	private Long idTransaction;

	/**
	 * Transaction Type.
	 */
	private TransactionType transactionType;

	/**
	 * Transaction Amount.
	 */
	private Double amount;

	/**
	 * Transaction Motif.
	 */
	private String motif;

	/**
	 * Opration Date.
	 */
	private Date date;

	/**
	 * Transaction Currency.
	 */
	private Currency currency;

	/**
	 * Transaction account.
	 */
	private AccountDTO account;


}
