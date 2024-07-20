package com.nagarro.account_management.exception;

public class InvalidCustomerException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		String resourceName;
		String fieldname;
		long fieldValue;

		public InvalidCustomerException(String resourceName, String fieldName, long fieldValue ) {
			super(String.format("%s not found with %s value as %s", resourceName, fieldName, fieldValue));
			this.resourceName=resourceName;
			this.fieldname=fieldName;
			this.fieldValue=fieldValue;
		}
		
}
