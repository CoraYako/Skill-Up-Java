package com.alkemy.wallet.transaction_record.dto;

import java.time.LocalDateTime;

public record TransactionReceiptResponse(
        Long operationNumber,
        String amount,
        String operationType,
        String description,
        Long destinationAccountNumber,
        LocalDateTime dateTime
) {
}
