package com.alkemy.wallet.transactionrecord.repository;

import com.alkemy.wallet.transactionrecord.domain.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {
}