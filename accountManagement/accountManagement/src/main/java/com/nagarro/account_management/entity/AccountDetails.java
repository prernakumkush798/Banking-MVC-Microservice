package com.nagarro.account_management.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigDecimalDeserializer;
import com.nagarro.account_management.utils.AccountStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountNumber;

	@NotNull(message = "Balance cannot be null")
	@Min(value = 0, message = "Balance should be greater than or equal to 0")
    @JsonDeserialize(using = BigDecimalDeserializer.class)
	private BigDecimal balance;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date openingDate;

	@Enumerated(EnumType.STRING)
	private AccountStatus status;

	@NotNull(message = "Customer id cannot be empty ")
    @Min(value = 1, message = "Customer ID must be greater than 0")
	private long customerNumber;
	
	transient private Customer customer;

}
