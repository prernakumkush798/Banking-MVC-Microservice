package com.nagarro.account_management.RequestEntity;

import java.math.BigDecimal;

import com.nagarro.account_management.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyRequestDto {
	    private BigDecimal amount;
	    private long accountId;
	    private Customer customer;

	   
	


}
