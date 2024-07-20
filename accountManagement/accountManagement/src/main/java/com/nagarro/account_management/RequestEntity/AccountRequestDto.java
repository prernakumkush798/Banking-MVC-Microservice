package com.nagarro.account_management.RequestEntity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigDecimalDeserializer;
import com.nagarro.account_management.utils.AccountStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {
	
		private long accountNumber;

		@JsonDeserialize(using = BigDecimalDeserializer.class)
		private BigDecimal balance;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		private Date openingDate;

		@Enumerated(EnumType.STRING)
		private AccountStatus status;

		private long customerNumber;

	}


