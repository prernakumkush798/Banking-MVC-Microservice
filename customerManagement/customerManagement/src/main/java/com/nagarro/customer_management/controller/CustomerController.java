package com.nagarro.customer_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.customer_management.dto.UpdateCustomerRequestDto;
import com.nagarro.customer_management.entity.Customer;
import com.nagarro.customer_management.service.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
	
	private final CustomerService service;
	
	
	@PostMapping
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer){
		Customer addCustomer =this.service.addCustomer(customer);
		return new ResponseEntity<>(addCustomer, HttpStatus.OK);
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody UpdateCustomerRequestDto request, @PathVariable int id){
		Customer updateCustomer=this.service.updateCustomer(request,id );
		return new ResponseEntity<>(updateCustomer,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers= this.service.getAllCustomer();
		return new ResponseEntity<>(customers,HttpStatus.OK);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Customer> getCustomerById( @PathVariable int id){
		Customer customer=this.service.getCustomerById(id);
		return new ResponseEntity<>(customer,HttpStatus.OK);
		
	}
	//softdelete, validate user exist or not --data stays in db 
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteCustomerById( @PathVariable int id){
		this.service.deleteCustomerById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
