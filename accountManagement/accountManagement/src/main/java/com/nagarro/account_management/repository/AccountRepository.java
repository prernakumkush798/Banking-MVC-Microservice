package com.nagarro.account_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.account_management.entity.AccountDetails;

@Repository
public interface AccountRepository extends JpaRepository<AccountDetails, Long>{

	List<AccountDetails> findByCustomerNumber(long customerNumber);
}
