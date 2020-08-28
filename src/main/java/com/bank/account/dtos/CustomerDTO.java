package com.bank.account.dtos;

import com.bank.account.enums.Country;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Client DTO.
 */
@Data
public class CustomerDTO {

	/**
	 * Id Client.
	 */
	private Long id;

	/**
	 * Customer First name.
	 */
	@ApiModelProperty("First name.")
	private String firstName;

	/**
	 * Customer last name.
	 */
	@ApiModelProperty("Last name.")
	private String lastname;

	/**
	 * Email Address of the customer.
	 */
	@ApiModelProperty("Customer email adress.")
	private String email;

	/**
	 * Address of the customer.
	 */
	@ApiModelProperty("Customer adress.")
	private String address;

	/**
	 * the ZIP code of the customer.
	 */
	@ApiModelProperty("Zip code.")
	private String zipCode;

	/**
	 * The city of the customer.
	 */
	@ApiModelProperty("City.")
	private String city;

	/**
	 * The country of the customer.
	 */
	@ApiModelProperty("Country.")
	private Country country;

}
