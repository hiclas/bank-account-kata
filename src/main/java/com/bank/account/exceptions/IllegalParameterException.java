package com.bank.account.exceptions;

import com.bank.account.common.Constants;

public class IllegalParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4264547781021105368L;

	/**
	 * Constructor for AmountNotAllowedException.
	 */
	public IllegalParameterException() {
		super(Constants.ILLEGAL_PARAMETERS);
	}

}
