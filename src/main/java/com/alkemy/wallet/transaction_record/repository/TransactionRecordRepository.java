package com.alkemy.wallet.transaction_record.repository;

import com.alkemy.wallet.account.domain.model.Account;
import com.alkemy.wallet.transaction_record.domain.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {
    Optional<TransactionRecord> findByOriginAccountAndOperationNumber(Account originAccount, Long operationNumber);
}