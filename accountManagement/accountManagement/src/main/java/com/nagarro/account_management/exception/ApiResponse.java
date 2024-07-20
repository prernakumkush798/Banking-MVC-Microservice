package com.nagarro.account_management.exception;

import lombok.Data;

@Data
public class ApiResponse {
	private String message;
	private Boolean success;
	 
	public ApiResponse(String message, Boolean success) {
		this.message=message;
		this.success=success;
		
		
	}

	

}
