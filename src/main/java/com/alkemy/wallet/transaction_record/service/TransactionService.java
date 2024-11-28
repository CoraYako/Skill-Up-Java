package com.alkemy.wallet.transaction_record.service;

import com.alkemy.wallet.account.domain.model.Account;
import com.alkemy.wallet.transaction_record.domain.OperationType;
import com.alkemy.wallet.transaction_record.dto.TransactionReceiptResponse;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    TransactionReceiptResponse recordTransaction(OperationType operationType, Account destinationAccount,
                                                 BigDecimal amount, String description);

    TransactionReceiptResponse getTransactionRecordFromAccount(Long accountNumber);

    List<TransactionReceiptResponse> getTransactionAccountHistory(Account sourceAccount);
}