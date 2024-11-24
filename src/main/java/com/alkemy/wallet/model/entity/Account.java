package com.alkemy.wallet.model.entity;

import com.alkemy.wallet.model.constant.AccountCurrencyEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "ACCOUNTS")
@SQLDelete(sql = "UPDATE accounts SET CLOSED=true WHERE ACCOUNT_NUMBER=?")
@SQLRestriction("CLOSED <> false")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private Long accountNumber;

    @Column(nullable = false, name = "CURRENCY_TYPE")
    @Enumerated(EnumType.STRING)
    private AccountCurrencyEnum currencyType;

    @Column(nullable = false, name = "TRANSACTION_LIMIT")
    private double transactionLimit;

    @Column(nullable = false, name = "BALANCE")
    private BigDecimal balance;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime openDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime lastModification;

    @Column(name = "CLOSED", nullable = false)
    private boolean closed;

    @OneToMany(mappedBy = "account", fetch = LAZY, cascade = ALL)
    private Set<Transaction> movements;

    public Account(AccountCurrencyEnum currencyType, double transactionLimit) {
        this.currencyType = currencyType;
        this.transactionLimit = transactionLimit;
        this.balance = BigDecimal.ZERO;
        this.openDate = LocalDateTime.now();
        this.closed = false;
        this.movements = new HashSet<>();
    }

    public Account() {
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public AccountCurrencyEnum getCurrencyType() {
        return currencyType;
    }

    public double getTransactionLimit() {
        return transactionLimit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public LocalDateTime getLastModification() {
        return lastModification;
    }

    public boolean isClosed() {
        return closed;
    }

    public Set<Transaction> getMovements() {
        return movements;
    }

    public void setTransactionLimit(double newLimit) {
        if (newLimit != 0)
            this.transactionLimit = newLimit;
    }

    public void setBalance(BigDecimal newBalance) {
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0)
            this.balance = newBalance;
    }

    public void setLastModification(LocalDateTime dateTime) {
        if (Objects.nonNull(dateTime))
            this.lastModification = dateTime;
    }
}

