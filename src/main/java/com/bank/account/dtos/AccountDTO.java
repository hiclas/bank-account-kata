package com.bank.account.dtos;

import lombok.Data;

/**
 * Account data transfer object.
 */
@Data
public class AccountDTO {

	/**
	 * The account identifier.
	 */
	private Long id;
	
	/**
	 * The account number.
	 */
	private Long accountNumber;

	/**
	 * The balance of the account.
	 */
	private Double balance;


	/**
	 * The owner of the account.
	 */
	private CustomerDTO customer;

}
