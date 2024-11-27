package com.alkemy.wallet.account.service;

import com.alkemy.wallet.account.domain.exception.InvalidAccountCurrencyType;
import com.alkemy.wallet.account.domain.model.Account;
import com.alkemy.wallet.account.dto.request.OpenAccountRequest;
import com.alkemy.wallet.account.dto.request.TransactionLimitModificationRequest;
import com.alkemy.wallet.account.dto.response.AccountDetailsResponse;
import com.alkemy.wallet.account.mapper.AccountMapper;
import com.alkemy.wallet.account.repository.AccountRepository;
import com.alkemy.wallet.customer.domain.Customer;
import com.alkemy.wallet.account.domain.exception.NullAccount;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final AccountMapper mapper;
    private final AccountFactory factory;

    public AccountServiceImpl(AccountRepository repository, AccountMapper mapper, AccountFactory factory) {
        this.repository = repository;
        this.mapper = mapper;
        this.factory = factory;
    }

    @Override
    public void openAccount(OpenAccountRequest request) {
        if (request.currencyType().trim().isEmpty())
            throw new InvalidAccountCurrencyType("Null currency type provided, account creation rejected");

        Account account = factory.getAccount(request.currencyType());
        repository.save(account);
    }

    @Override
    public AccountDetailsResponse modifyTransactionLimit(TransactionLimitModificationRequest request) {
        Account account = findAccountByAccountNumber(request.targetAccountNumber());
        account.setTransactionLimit(request.newTransactionLimit());
        repository.save(account);
        return mapper.toDto(account);
    }

    @Override
    public void deactivateAccount(Long accountNumber) {
        Account account = findAccountByAccountNumber(accountNumber);
        account.deactivateAccount();
        repository.save(account);
    }

    @Override
    public List<AccountDetailsResponse> getCustomerAssociatedAccounts(Customer loggedCustomer) {
        return loggedCustomer.getAssociatedAccounts()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    private Account findAccountByAccountNumber(Long accountNumber) {
        return repository.findById(accountNumber)
                .orElseThrow(() -> new NullAccount("Account not found for account number " + accountNumber));
    }
}
