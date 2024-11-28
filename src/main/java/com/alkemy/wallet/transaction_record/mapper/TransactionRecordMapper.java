package com.alkemy.wallet.transaction_record.mapper;

import com.alkemy.wallet.transaction_record.domain.TransactionRecord;
import com.alkemy.wallet.transaction_record.dto.TransactionReceiptResponse;
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