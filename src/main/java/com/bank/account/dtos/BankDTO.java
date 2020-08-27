package com.bank.account.dtos;

import lombok.Data;

/**
 * Bank DTO.
 */
@Data
public class BankDTO {

	/**
	 * The bank identifier.
	 */
	private Long id;
	
	/**
	 * Bank Name.
	 */
	private String bankName;

	/**
	 * Bank Identifier Code.
	 */
	private String bankAccountNumber;

}
