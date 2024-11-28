package com.alkemy.wallet.transaction_record.domain;

import com.alkemy.wallet.account.domain.model.Account;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTIONS")
public class TransactionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPERATION_NUMBER")
    private Long operationNumber;

    @Column(nullable = false, name = "OPERATION_TYPE")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column(nullable = false, name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DESCRIPTION")
    private String description;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "DATE")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORIGIN_ACCOUNT")
    private Account originAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DESTINATION_ACCOUNT")
    private Account destinationAccount;

    public TransactionRecord(OperationType operationType, Account destinationAccount, Account originAccount,
                             BigDecimal amount, String description) {
        this.operationType = operationType;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.description = description;
        this.date = LocalDateTime.now();
    }

    public TransactionRecord() {
    }

    public Long getOperationNumber() {
        return operationNumber;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public Account getOriginAccount() {
        return originAccount;
    }
}
