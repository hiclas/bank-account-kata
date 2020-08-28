package com.bank.account.exceptions;


import com.bank.account.common.Constants;

public class AmountNotAllowedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8226786478970759387L;

	/**
	 * Constructor for AmountNotAllowedException.
	 */
	public AmountNotAllowedException() {
		super(Constants.AMOUNT_NOT_ALLOWED);
	}

}
