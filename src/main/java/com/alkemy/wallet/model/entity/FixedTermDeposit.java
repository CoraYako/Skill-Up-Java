package com.alkemy.wallet.model.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "FIXED_TERM_DEPOSITS")
public class FixedTermDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, name = "INVESTMENT_AMOUNT")
    private BigDecimal investmentAmount;

    @Column(nullable = false, name = "INTEREST_EARNED")
    private BigDecimal interestEarned;

    @Column(nullable = false, name = "DURATION_IN_DAYS")
    private int fixedTermDurationInDays;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "CREATED_AT")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "CLOSING_DATE")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SOURCE_ACCOUNT_NUMBER")
    private Account sourceAccount;

    public FixedTermDeposit(BigDecimal investmentAmount, int fixedTermDurationInDays, Account sourceAccount) {
        this.investmentAmount = investmentAmount;
        this.sourceAccount = sourceAccount;
    }

    public FixedTermDeposit() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getInvestmentAmount() {
        return investmentAmount;
    }

    public BigDecimal getInterestEarned() {
        return interestEarned;
    }

    public int getFixedTermDurationInDays() {
        return fixedTermDurationInDays;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }
}
