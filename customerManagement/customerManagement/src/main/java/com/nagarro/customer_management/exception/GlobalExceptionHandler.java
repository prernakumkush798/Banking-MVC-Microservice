package com.nagarro.customer_management.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	//saga pattern
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> xyz(ResourceNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse apiresponse= new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ApiResponse> abc(CustomerNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse apiresponse= new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
		
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgsNotValid(MethodArgumentNotValidException ex){
		Map<String, String> response=new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
		
	}
	

}
