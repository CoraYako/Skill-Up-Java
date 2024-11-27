package com.alkemy.wallet.account.service;

import com.alkemy.wallet.account.domain.exception.InvalidAccountCurrencyType;
import com.alkemy.wallet.account.domain.model.Account;
import com.alkemy.wallet.account.domain.model.CurrencyType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AccountFactory {
    Map<String, Account> accounts = new HashMap<>();

    public synchronized Account getAccount(String accountType) {
        Account account = accounts.get(accountType);

        if (Objects.nonNull(account))
            return account;

        switch (accountType) {
            case "ARS":
                account = new Account(CurrencyType.ARS, 300000.0);
                accounts.put(CurrencyType.ARS.toString(), account);
                break;
            case "USD":
                account = new Account(CurrencyType.USD, 1000.0);
                accounts.put(CurrencyType.USD.toString(), account);
                break;
            default:
                throw new InvalidAccountCurrencyType("Invalid currency type provided, account creation rejected");
        }

        return account;
    }
}
