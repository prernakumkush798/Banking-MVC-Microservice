package com.nagarro.customer_management.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nagarro.customer_management.dto.UpdateCustomerRequestDto;
import com.nagarro.customer_management.entity.AccountDetails;
import com.nagarro.customer_management.entity.Customer;
import com.nagarro.customer_management.exception.ResourceNotFoundException;
import com.nagarro.customer_management.repository.CustomerRepository;

import lombok.Data;

@Service
@Data
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository repository;
	private final ModelMapper modelMapper;
	private final WebClient.Builder webClient;

	@Override
	public Customer addCustomer(Customer customer) {
		Customer saveCustomer = this.repository.save(customer);
		return saveCustomer;
	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customers = this.repository.findAll();
		return customers;

	}

	@Override
	public Customer getCustomerById(int id) {
		Customer customer = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer id ", id));
		return customer;
	}

	@Override
	public void deleteCustomerById(int id) {
		Customer customer = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Id", id));

		List<AccountDetails> accounts = webClient.build().get().uri("http://localhost:8089/account/customer/" + id)
				.retrieve().bodyToMono(new ParameterizedTypeReference<List<AccountDetails>>() {
				}).block();

		accounts.forEach(account -> {
			webClient.build().delete().uri("http://localhost:8089/account/" + account.getAccountNumber()).retrieve()
					.bodyToMono(Void.class).block();
		});

		this.repository.delete(customer);
	}

	@Override
	public Customer updateCustomer(UpdateCustomerRequestDto request, int customerId) {
		Customer customer = this.repository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Id", customerId));
		modelMapper.map(request, customer);

		Customer updatedCustomer = this.repository.save(customer);
		return updatedCustomer;
	}

}
