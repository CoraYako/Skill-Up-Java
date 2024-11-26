package com.alkemy.wallet.model.dto.request;

public record TransferRequest(
        String amountToSend,
        String description,
        Long destinationAccountNumber
) {
}
