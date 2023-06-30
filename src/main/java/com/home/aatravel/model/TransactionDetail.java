package com.home.aatravel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransactionDetail {

    private String description;

    private BigDecimal amount;
}
