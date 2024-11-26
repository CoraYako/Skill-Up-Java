package com.alkemy.wallet.transactionrecord.dto;

import java.time.LocalDateTime;

public record TransactionReceiptResponse(
        Long operationNumber,
        String amount,
        String operationType,
        String description,
        Long destinationAccountNumber,
        String destinationClientName,
        LocalDateTime dateTime
) {
}
