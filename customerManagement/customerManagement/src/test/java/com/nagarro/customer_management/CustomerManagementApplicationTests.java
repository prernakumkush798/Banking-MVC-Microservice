package com.nagarro.customer_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.nagarro.customer_management.entity.Customer;
import com.nagarro.customer_management.repository.CustomerRepository;
import com.nagarro.customer_management.service.CustomerService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class CustomerManagementApplicationTests {

	@Test
	void contextLoads() {
	}
	@MockBean
	public CustomerRepository repository;
	
	@Autowired
	public CustomerService service;
	
	
	
	@Test
	public void getAllCustomer(){
		when(repository.findAll()).thenReturn(Arrays.asList(new Customer(1000000000, "Prerna", 24, "Delhi", "AXXXYY", "prerna@email.com", LocalDate.of(2000, 1, 4), "890098765", "Male", "Indian", "Private")));
		assertEquals(1,service.getAllCustomer().size());
	}

}
