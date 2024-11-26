package com.alkemy.wallet.transactionrecord.mapper;

import com.alkemy.wallet.transactionrecord.domain.TransactionRecord;
import com.alkemy.wallet.transactionrecord.dto.TransactionReceiptResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionRecordMapper {
    public TransactionReceiptResponse toDto(TransactionRecord transactionRecord) {
        return new TransactionReceiptResponse(
                transactionRecord.getOperationNumber(),
                transactionRecord.getAmount().toString(),
                transactionRecord.getOperationType().toString(),
                transactionRecord.getDescription(),
                transactionRecord.getDestinationAccount().getAccountNumber(),
                transactionRecord.getDate()
        );
    }
}