package com.nagarro.customer_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.customer_management.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	

}
