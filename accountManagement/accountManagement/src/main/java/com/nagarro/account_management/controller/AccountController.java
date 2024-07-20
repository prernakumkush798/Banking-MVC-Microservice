package com.nagarro.account_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.account_management.RequestEntity.AccountRequestDto;
import com.nagarro.account_management.RequestEntity.MoneyRequestDto;
import com.nagarro.account_management.ResponseEntity.AccountResponseDto;
import com.nagarro.account_management.entity.AccountDetails;
import com.nagarro.account_management.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
@ApiResponse(description = "Account Management API's")
public class AccountController {
	
	private AccountService service;
	
	@PostMapping("/add")
	@Operation(tags = "Add money")
	public ResponseEntity<AccountDetails> addMoney(@Valid @RequestBody MoneyRequestDto request){
		AccountDetails addMoneyToAccount=this.service.addMoney(request.getAmount(), request.getAccountId(), request.getCustomer());
		return new ResponseEntity<>(addMoneyToAccount, HttpStatus.OK);
		
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<AccountDetails> debitMoney(@Valid @RequestBody MoneyRequestDto request){
		AccountDetails account=this.service.withdrawMoney(request.getAmount(), request.getAccountId(), request.getCustomer());
		return new ResponseEntity<>(account,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AccountResponseDto> addAccount(@Valid @RequestBody AccountRequestDto account){
		AccountResponseDto details=this.service.addAccount(account);
		return new ResponseEntity<>(details,HttpStatus.OK);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AccountDetails> getAccount(@PathVariable int id){
		AccountDetails account= this.service.getAccountByAccountNumber(id);
		return new ResponseEntity<>(account,HttpStatus.OK);
		
	}
	
	@GetMapping("customer/{customerId}")
	public ResponseEntity<List<AccountDetails>> getAccountOfCustomer(@PathVariable long customerId){
		List<AccountDetails> accounts= this.service.getAccountsofCustomer(customerId);
		return new ResponseEntity<>(accounts,HttpStatus.OK);
	}
	
	@DeleteMapping("customer/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable int id){
		this.service.deleteAccountByCustomerId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteAccountById(@PathVariable long id){
		this.service.deleteAccountById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDetails>> getAllAccounts(){
		List<AccountDetails> allAccounts=this.service.getAllAccounts();
		return new ResponseEntity<>(allAccounts,HttpStatus.OK);
	}
	
	
	
	

}
