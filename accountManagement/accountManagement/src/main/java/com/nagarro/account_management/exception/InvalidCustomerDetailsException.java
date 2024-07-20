package com.nagarro.account_management.exception;

public class InvalidCustomerDetailsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	 public InvalidCustomerDetailsException(String message) {
		 super(message);
	 }
	

}
