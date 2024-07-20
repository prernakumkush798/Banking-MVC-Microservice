package com.nagarro.customer_management.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Name cannot be empty")
	private String name;
	
	@NotNull(message = "Age cannot be null")
	@Min(value = 0, message = "Age should be greater than or equal to 0")
	private Integer age;
	
	private String address;
	@NotEmpty(message = "PAN card cannot be empty")
	private String panCard;
	
	@Email(message = "Enter valid email id")
	private String email;

	@NotNull(message = "Date of Birth cannot be null")
	@Past(message = "Date should be of past value")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	
	@NotEmpty(message = "Phone number cannot be empty")
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
	private String phoneNumber;
	
	@NotEmpty(message="gender cannot be empty")
	private String gender;
	
	@NotEmpty(message="nationality cannot be empty")
	private String nationality;
	
	@NotEmpty(message="occupation cannot be empty")
	private String occupation;
	
//	transient private List<AccountDetails> account;

}
