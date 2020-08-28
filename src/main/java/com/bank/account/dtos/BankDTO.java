package com.bank.account.dtos;

import lombok.Data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bank DTO.
 */
@Data
@ApiModel("Bank")
public class BankDTO {

	/**
	 * The bank identifier.
	 */
	private Long id;
	
	/**
	 * Bank Name.
	 */
	@ApiModelProperty("Bank Name.")
	private String bankName;

	/**
	 * Bank Identifier Code.
	 */
	 @ApiModelProperty("Bank account number.")
	private String bankAccountNumber;

}
