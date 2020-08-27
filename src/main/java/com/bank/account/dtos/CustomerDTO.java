package com.bank.account.dtos;

import com.bank.account.enums.Country;

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
	private String firstName;

	/**
	 * Customer last name.
	 */
	private String lastname;

	/**
	 * Email Address of the customer.
	 */
	private String email;

	/**
	 * Address of the customer.
	 */
	private String address;

	/**
	 * the ZIP code of the customer.
	 */
	private String zipCode;

	/**
	 * The city of the customer.
	 */
	private String city;

	/**
	 * The country of the customer.
	 */
	private Country country;

}
