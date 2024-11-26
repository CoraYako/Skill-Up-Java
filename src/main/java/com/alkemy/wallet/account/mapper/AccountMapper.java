package com.alkemy.wallet.account.mapper;

import com.alkemy.wallet.account.domain.Account;
import com.alkemy.wallet.account.dto.response.AccountDetailsResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountDetailsResponse toDto(Account account) {
        return new AccountDetailsResponse(
                account.getAccountNumber(),
                account.getCurrencyType().toString(),
                account.getBalance().toString(),
                account.getTransactionLimit()
        );
    }
}