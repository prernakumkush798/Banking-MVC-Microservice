package com.nagarro.customer_management.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateCustomerRequestDto {


	private String name;
	
	private Integer age;
	
	private String address;
	private String panCard;
	
	@Email(message = "Enter valid email id")
	private String email;

	@Past(message = "Date should be of past value")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
	private String phoneNumber;
	
	private String gender;
	
	private String nationality;
	
	private String occupation;

}
