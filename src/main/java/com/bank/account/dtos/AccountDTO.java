package com.bank.account.dtos;

import lombok.Data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Account data transfer object.
 */
@Data
@ApiModel("Account")
public class AccountDTO {

	/**
	 * The account identifier.
	 */
	@ApiModelProperty("Account identifier.")
	private Long id;
	
	/**
	 * The account number.
	 */
	@ApiModelProperty("Account Number.")
	private Long accountNumber;

	/**
	 * The balance of the account.
	 */
	@ApiModelProperty("Balance.")
	private Double balance;


	/**
	 * The owner of the account.
	 */
	 @ApiModelProperty("Customer linked to this account.")
	private CustomerDTO customer;

}
