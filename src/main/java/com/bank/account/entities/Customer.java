package com.bank.account.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bank.account.enums.Country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The class Customer entity containing customer details.
 *
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	/**
	 * The identifier of the customer.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The first name of the customer.
	 */
	@Column(nullable = false)
	private String firstName;

	/**
	 * The last name of the customer.
	 */
	@Column(nullable = false)
	private String lastName;

	/** 
	 * The email adresse of the customer.
	 */
	@Column(nullable = false)
	private String email;

	/** 
	 * The adresse of the customer.
	 */
	@Column(nullable = false)
	private String address;

	/**
	 * The zip code of the customer.
	 */
	@Column(nullable = false)
	private String zipCode;

	/**
	 * The city of the customer.
	 */
	@Column(nullable = false)
	private String city;

	/**
	 * The country of the customer.
	 */
	@Column(nullable = false)
	private Country country;

}
