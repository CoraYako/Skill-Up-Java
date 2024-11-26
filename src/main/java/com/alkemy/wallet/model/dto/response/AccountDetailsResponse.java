package com.alkemy.wallet.model.dto.response;

public record AccountDetailsResponse(
        Long accountNumber,
        String currencyType,
        String balance,
        double transactionLimit
) {
}