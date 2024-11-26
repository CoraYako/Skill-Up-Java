package com.alkemy.wallet.account.dto.request;

public record TransferRequest(
        String amountToSend,
        String description,
        Long destinationAccountNumber
) {
}
