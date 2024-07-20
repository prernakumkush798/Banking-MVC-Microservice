package com.nagarro.customer_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.nagarro.customer_management.entity.Customer;
import com.nagarro.customer_management.repository.CustomerRepository;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTests {
	
	@MockBean
	public CustomerRepository repository;
	
	@Autowired
	public CustomerServiceImpl service;
	
	
	
	 @Test
	    public void getAllCustomer() {
	        Customer customer = new Customer(1000000000, "Prerna", 24, "Delhi", "AXXXYY", "prerna@email.com", LocalDate.of(2000, 1, 4), "890098765", "Male", "Indian", "Private");
	        
	        when(repository.findAll()).thenReturn(Arrays.asList(customer));
	        
	        assertEquals(1, service.getAllCustomer().size());
	    }

}
