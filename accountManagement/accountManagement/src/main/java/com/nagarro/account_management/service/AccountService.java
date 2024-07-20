package com.nagarro.account_management.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.account_management.RequestEntity.AccountRequestDto;
import com.nagarro.account_management.ResponseEntity.AccountResponseDto;
import com.nagarro.account_management.entity.AccountDetails;
import com.nagarro.account_management.entity.Customer;

@Service
public interface AccountService {

	AccountResponseDto addAccount(AccountRequestDto account);

	AccountDetails updateAccountByAccountNumber(AccountDetails account, long accountNumber);

	AccountDetails getAccountByAccountNumber(long number);

	List<AccountDetails> getAccountsofCustomer(long cutomerId);

	void deleteAccountByCustomerId(long customerId);

	List<AccountDetails> getAllAccounts();
	
	void deleteAccountById(long accountId);

	AccountDetails addMoney(BigDecimal amount, long accountId, Customer customer);

	AccountDetails withdrawMoney(BigDecimal amount, long accountId, Customer customer);

}
