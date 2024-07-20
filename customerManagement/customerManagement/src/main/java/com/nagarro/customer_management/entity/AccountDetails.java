package com.nagarro.customer_management.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nagarro.customer_management.utils.AccountStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDetails {

	
	private long accountNumber;

	private double balance;

	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date openingDate;

	@Enumerated(EnumType.STRING)
	private AccountStatus status;
	
	private long customerId;

}
