package com.nagarro.account_management;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class AccountManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagementApplication.class, args);
	}
	
	@Bean
	public WebClient.Builder getWebClient(){
		return WebClient.builder();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
