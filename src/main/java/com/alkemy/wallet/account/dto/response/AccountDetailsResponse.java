package com.alkemy.wallet.account.dto.response;

public record AccountDetailsResponse(
        Long accountNumber,
        String currencyType,
        String balance,
        double transactionLimit
) {
}