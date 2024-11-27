package com.alkemy.wallet.fixedterm.domain;

import com.alkemy.wallet.account.domain.exception.NullAccount;
import com.alkemy.wallet.account.domain.model.Account;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "FIXED_TERM_DEPOSITS")
public class FixedTermDeposit {
    private static final int MIN_DAYS_FOR_FIXED_TERM = 30;
    private static final int MAX_DAYS_FOR_FIXED_TERM = 90;
    protected static final BigDecimal INTEREST_RATE = new BigDecimal("0.05");

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
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SOURCE_ACCOUNT_NUMBER")
    private Account sourceAccount;

    public FixedTermDeposit(int fixedTermDurationInDays, BigDecimal investmentAmount, Account sourceAccount) {
        this.validateDataBeforeCreation(fixedTermDurationInDays, investmentAmount, sourceAccount);

        this.initializeDatePeriodForFixedTerm(fixedTermDurationInDays);

        this.interestEarned = calculateInterest(investmentAmount, fixedTermDurationInDays);
        this.fixedTermDurationInDays = fixedTermDurationInDays;
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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    private void initializeDatePeriodForFixedTerm(long days) {
        this.startDate = LocalDateTime.now();
        this.endDate = startDate.plusDays(days);
    }

    private BigDecimal calculateInterest(BigDecimal amount, long days) {
        BigDecimal interestGenerated = BigDecimal.ZERO;
        for (int i = 0; i < days; i++) {
            interestGenerated = interestGenerated.add(amount.multiply(INTEREST_RATE));
        }
        return interestGenerated;
    }

    private void validateInvestmentAmount(BigDecimal investmentAmount) {
        if (Objects.isNull(investmentAmount) || investmentAmount.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidDepositAmount("Invalid amount to invest for fixed term");
    }

    private void validateSourceAccount(Account sourceAccount) {
        if (Objects.isNull(sourceAccount))
            throw new NullAccount("The source account to init fixed term is invalid or null");
    }

    private void validateDaysRangeForFixedTerm(long days) {
        if (days < MIN_DAYS_FOR_FIXED_TERM || days > MAX_DAYS_FOR_FIXED_TERM)
            throw new InvalidDaysRange("Days for fixed term out of range: min is 30 days and max is 90 days");
    }

    private void validateDataBeforeCreation(int durationInDays, BigDecimal investmentAmount, Account sourceAccount) {
        validateDaysRangeForFixedTerm(durationInDays);
        validateInvestmentAmount(investmentAmount);
        validateSourceAccount(sourceAccount);
    }
}
