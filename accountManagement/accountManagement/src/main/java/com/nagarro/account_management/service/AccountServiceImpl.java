package com.nagarro.account_management.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.nagarro.account_management.RequestEntity.AccountRequestDto;
import com.nagarro.account_management.ResponseEntity.AccountResponseDto;
import com.nagarro.account_management.entity.AccountDetails;
import com.nagarro.account_management.entity.Customer;
import com.nagarro.account_management.exception.InsufficientBalanceException;
import com.nagarro.account_management.exception.InvalidCustomerDetailsException;
import com.nagarro.account_management.exception.InvalidCustomerException;
import com.nagarro.account_management.exception.ResourceNotFoundException;
import com.nagarro.account_management.repository.AccountRepository;

import lombok.Data;

@Service
@Data
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private WebClient.Builder webClient;

	@Override
	public AccountResponseDto addAccount(AccountRequestDto accountDto) {
		AccountDetails accountInfo = this.modelMapper.map(accountDto, AccountDetails.class);
		Customer isCustomer;
		try {
			isCustomer = webClient.build().get().uri("http://localhost:8090/customer/" + accountDto.getCustomerNumber())
					.retrieve().bodyToMono(Customer.class).block();
		} catch (WebClientResponseException ex) {
			throw new InvalidCustomerException("Customer", "Customer Id", accountDto.getCustomerNumber());
		}
		AccountDetails accountDetails = this.repository.save(accountInfo);

		return this.modelMapper.map(accountDetails, AccountResponseDto.class);
	}

	@Override
	public AccountDetails updateAccountByAccountNumber(AccountDetails account, long accountNumber) {
		if (!this.repository.existsById(accountNumber)) {
			new ResourceNotFoundException("Account", "number", accountNumber);
		}

		AccountDetails updatedAccount = this.repository.save(account);
		return updatedAccount;

	}

	@Override
	public AccountDetails getAccountByAccountNumber(long number) {
		AccountDetails account = this.repository.findById(number)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "number", number));
		Customer customer;
		try {
		customer = webClient.build().get().uri("http://localhost:8090/customer/" + account.getCustomerNumber())
				.retrieve().bodyToMono(Customer.class).block();
		}
		catch (WebClientResponseException ex) {
			throw new InvalidCustomerException("Customer", "Customer Id", account.getCustomerNumber());
		}
		account.setCustomer(customer);
		return account;
	}

	@Override
	public void deleteAccountByCustomerId(long customerId) {
		List<AccountDetails> details = this.repository.findByCustomerNumber(customerId);
		details.stream().forEach(item -> this.repository.delete(item));

	}
	
	@Override
	public void deleteAccountById(long accountId) {
		AccountDetails account=this.repository.findById(accountId).orElseThrow(()->new ResourceNotFoundException("Account", "Account Id", accountId));
		this.repository.delete(account);
	}

	@Override
	public List<AccountDetails> getAccountsofCustomer(long cutomerId) {
		List<AccountDetails> accounts = this.repository.findByCustomerNumber(cutomerId);
		return accounts;
	}

	@Override
	public List<AccountDetails> getAllAccounts() {
		List<AccountDetails> listOfAccounts = this.repository.findAll();
		List<Customer> customers = webClient.build().get().uri("http://localhost:8090/customer") 
				.retrieve().bodyToMono(new ParameterizedTypeReference<List<Customer>>() {
				}).block();
		Map<Long, Customer> customerMap = customers.stream()
				.collect(Collectors.toMap(Customer::getId, customer -> customer));

		listOfAccounts.forEach(account -> {
			Customer customer = customerMap.get(account.getCustomerNumber());
			account.setCustomer(customer);
		});

		return listOfAccounts;
	}

	@Override
	public AccountDetails addMoney(BigDecimal amount, long accountId, Customer customer) {
		BigDecimal newAmount = new BigDecimal(amount.toString());
		BigDecimal sum = new BigDecimal("0");
		AccountDetails account = this.repository.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "Account number", accountId));
		Customer isCustomer;
		try {
			isCustomer = webClient.build().get().uri("http://localhost:8090/customer/" + customer.getId()).retrieve()
					.bodyToMono(Customer.class).block();
		} catch (WebClientResponseException ex) {
			throw new InvalidCustomerException("Customer", "Customer Id", customer.getId());
		}
		if (isCustomer.getId() == customer.getId() && isCustomer.getPanCard().equals(customer.getPanCard())) {
			account.setCustomer(isCustomer);
			BigDecimal currentBalance = account.getBalance();
			sum = currentBalance.add(newAmount);

			account.setBalance(sum);
			this.repository.save(account);

		} else {
			throw new ResourceNotFoundException("Account", "AccountId ", accountId);
		}
		return account;
	}

	@Override
	public AccountDetails withdrawMoney(BigDecimal amount, long accountId, Customer customer) {
		BigDecimal newAmount = new BigDecimal(amount.toString());
		BigDecimal sum = new BigDecimal("0");
		AccountDetails account = this.repository.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "Account Id", accountId));
		Customer isCustomer;
		try {
			isCustomer = webClient.build().get().uri("http://localhost:8090/customer/" + customer.getId()).retrieve()
					.bodyToMono(Customer.class).block();
		} catch (WebClientResponseException ex) {
			throw new InvalidCustomerException("Customer", "Customer Id", customer.getId());
		}
		if (isCustomer.getId() == customer.getId() && isCustomer.getPanCard().equals(customer.getPanCard())) {
			BigDecimal currentBalance = account.getBalance();
			account.setCustomer(isCustomer);

			if (currentBalance.compareTo(newAmount) < 0) {
				throw new InsufficientBalanceException("Insufficient balance to perform the operation.");
			}
			sum = currentBalance.subtract(newAmount);

			account.setBalance(sum);
			this.repository.save(account);

		} else {
			throw new InvalidCustomerDetailsException("Customer details does not match with provided details");
		}
		return account;
	}

}
