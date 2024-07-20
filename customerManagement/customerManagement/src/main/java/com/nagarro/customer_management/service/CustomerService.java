package com.nagarro.customer_management.service;

import java.util.List;

import com.nagarro.customer_management.dto.UpdateCustomerRequestDto;
import com.nagarro.customer_management.entity.Customer;


public interface CustomerService {
	
	 Customer addCustomer(Customer customer);
	 Customer updateCustomer(UpdateCustomerRequestDto request,int id);
	 List<Customer> getAllCustomer();
	 Customer getCustomerById(int id);
	 void deleteCustomerById(int id);
	 
	 
	 

}
