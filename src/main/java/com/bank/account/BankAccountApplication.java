package com.bank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The class main responsible for starting the the Spring application context,
 * Enabling auto configuration and component scanning (The three options are in
 * the SpringBootApplication annotation.
 *
 */
@SpringBootApplication
public class BankAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}

}
