package com.alkemy.wallet.transaction_record.service;

import com.alkemy.wallet.account.domain.exception.NullAccount;
import com.alkemy.wallet.account.domain.model.Account;
import com.alkemy.wallet.fixedterm.domain.InvalidDepositAmount;
import com.alkemy.wallet.transaction_record.TransactionNotFound;
import com.alkemy.wallet.transaction_record.domain.OperationType;
import com.alkemy.wallet.transaction_record.domain.TransactionRecord;
import com.alkemy.wallet.transaction_record.dto.TransactionReceiptResponse;
import com.alkemy.wallet.transaction_record.mapper.TransactionRecordMapper;
import com.alkemy.wallet.transaction_record.repository.TransactionRecordRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRecordRepository repository;
    private final TransactionRecordMapper mapper;

    public TransactionServiceImpl(TransactionRecordMapper mapper, TransactionRecordRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public TransactionReceiptResponse recordTransaction(OperationType operationType, Account destinationAccount,
                                                        Account originAccount, BigDecimal amount, String description) {
        validateAccountAndAmountBeforeCreation(destinationAccount, amount);
        TransactionRecord transactionRecord = new TransactionRecord(operationType, destinationAccount,
                originAccount, amount, description);
        repository.save(transactionRecord);
        return mapper.toDto(transactionRecord);
    }

    @Override
    public TransactionReceiptResponse getTransactionRecordFromAccount(Account sourceAccount, Long transactionOperationNumber) {
        if (Objects.isNull(sourceAccount))
            throw new NullAccount("The provided account is invalid or null");

        String errorMessage = "Transaction not found for operation number " + transactionOperationNumber
                + " in account " + sourceAccount.getAccountNumber();

        TransactionRecord transactionRecord = repository
                .findByOriginAccountAndOperationNumber(sourceAccount, transactionOperationNumber)
                .orElseThrow(() -> new TransactionNotFound(errorMessage));

        return mapper.toDto(transactionRecord);
    }

    @Override
    public List<TransactionReceiptResponse> getTransactionAccountHistory(Account sourceAccount) {
        return sourceAccount.getMovements()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    private void validateAccountAndAmountBeforeCreation(Account destinationAccount, BigDecimal amount) {
        if (Objects.isNull(destinationAccount))
            throw new NullAccount("Transaction record can't be created: null account");
        if (Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidDepositAmount("Transaction record can't be created: invalid amount");
    }
}