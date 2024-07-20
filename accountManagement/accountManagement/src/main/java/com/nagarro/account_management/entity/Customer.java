package com.nagarro.account_management.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

	
	private long id;

	private String name;
	
	
	private int age;
	
	private String address;
	private String panCard;
	
	private String email;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dateOfBirth;
	
	private String phoneNumber;
	
	private String gender;
	
	private String nationality;
	
	private String occupation;
	
//	 private List<AccountDetails> account;

}
