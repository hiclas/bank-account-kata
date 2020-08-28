package com.bank.account.exceptions;

import com.bank.account.common.Constants;

public class AccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4364547781021105368L;

	/**
	 * Constructor for AmountNotAllowedException.
	 */
	public AccountNotFoundException() {
		super(Constants.ACCOUNT_NOT_FOUND);
	}

	/**
	 * Constructor with exception message.
	 */
	public AccountNotFoundException(String msg) {
		super(msg);
	}
}
