package com.nagarro.customer_management.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

	private long accountNumber;

	private double balance;

	private Date openingDate;

	private String status;

	private long customerId;

}
