package com.alkemy.wallet.account.dto.request;

public record TransactionLimitModificationRequest(
        Long targetAccountNumber,
        String newTransactionLimit
) {
}
