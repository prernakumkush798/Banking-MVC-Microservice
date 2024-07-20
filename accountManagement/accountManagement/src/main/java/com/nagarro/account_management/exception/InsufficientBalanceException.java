package com.nagarro.account_management.exception;

public class InsufficientBalanceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 String message;
	 

	public InsufficientBalanceException(String message) {
		super(message);
	}

}
