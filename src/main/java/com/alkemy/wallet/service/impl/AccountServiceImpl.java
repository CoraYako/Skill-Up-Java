package com.alkemy.wallet.service.impl;


import com.alkemy.wallet.controller.exception.Mistake;
import com.alkemy.wallet.model.dto.request.AccountRequestDto;
import com.alkemy.wallet.model.dto.response.AccountBalanceResponseDto;
import com.alkemy.wallet.model.dto.response.AccountResponseDto;
import com.alkemy.wallet.model.entity.Account;
import com.alkemy.wallet.model.entity.AccountCurrencyEnum;
import com.alkemy.wallet.model.entity.User;
import com.alkemy.wallet.model.mapper.AccountMapper;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.IAuthService;
import com.alkemy.wallet.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.alkemy.wallet.model.entity.AccountCurrencyEnum.*;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository repository;
    private final IUserService userService;
    private final AccountMapper mapper;
    private final IAuthService authService;

    @Override
    public Account getAccountById(long id) {
        Optional<Account> response = repository.findById(id);
        if (response.isEmpty())
            throw new NoSuchElementException(String.format("Account with id %s was not found", id));
        return response.get();
    }

    @Override
    public AccountResponseDto save(AccountRequestDto request, String token) {
        User loggedUser = authService.getUserFromToken(token);
        loggedUser.getAccounts().forEach(account -> {
            if (request.getCurrency().equalsIgnoreCase(account.getCurrency().name()))
                throw new EntityExistsException(String.format("An account in %s already exist", request.getCurrency().toUpperCase()));
        });
        AccountCurrencyEnum currency;
        double transactionLimit;
        if (specificTypeOfCurrency(request.getCurrency()).equals(ARS)) {
            currency = ARS;
            transactionLimit = 300000.0;
        } else {
            currency = USD;
            transactionLimit = 1000.0;
        }
        Account account = mapper.dto2Entity(request, currency, transactionLimit, loggedUser);
        userService.addAccount(loggedUser, account);
        return mapper.entity2Dto(repository.save(account));
    }

    @Override
    public List<Account> createUserAccounts(User user) {
        Account USDAccount = new Account();
        USDAccount.setUser(user);
        USDAccount.setCreationDate(LocalDateTime.now());
        USDAccount.setBalance(0.0);
        USDAccount.setCurrency(USD);
        USDAccount.setTransactionLimit(1000.0);

        Account ARSAccount = new Account();
        ARSAccount.setUser(user);
        ARSAccount.setCreationDate(LocalDateTime.now());
        ARSAccount.setBalance(0.0);
        ARSAccount.setCurrency(ARS);
        ARSAccount.setTransactionLimit(300000.0);

        repository.save(USDAccount);
        repository.save(ARSAccount);

        List<Account> accountList = new ArrayList<>();
        accountList.add(USDAccount);
        accountList.add(ARSAccount);

        return accountList;
    }

    @Override
    public Account getByCurrencyAndUserId(String currency, Long userId) {
        Optional<Account> response = repository.findByCurrencyAndUserId(currency, userId);
        if (response.isEmpty())
            throw new NoSuchElementException("The account doesn't exist or the user is not present");
        return response.get();
    }

    @Override
    public void editBalanceAndSave(Account account, Double newBalance) {
        account.setBalance(newBalance);
        repository.save(account);
    }

    @Override
    public List<AccountBalanceResponseDto> getAccountBalance(String token) {
        User loggedUser = authService.getUserFromToken(token);
        List<Account> accounts = getAccountsByUserId(loggedUser.getId());

        List<AccountBalanceResponseDto> accountBalanceList = new ArrayList<>();

        for (Account account : accounts) {
            AccountBalanceResponseDto balanceResponse;

            LocalDate dateDB = LocalDate.of
                    (account.getCreationDate().getYear(),
                            account.getCreationDate().getMonth(),
                            account.getCreationDate().getDayOfWeek().getValue());

            Period duration = Period.between(dateDB, LocalDate.now());
            balanceResponse = new AccountBalanceResponseDto();
            if (duration.getMonths() > 0) {
                balanceResponse.setFixedTermDeposit(account.getBalance() * (282 * duration.getMonths()));
            }
            if (account.getCurrency().equals(ARS)) {
                balanceResponse.setBalanceUsd(account.getBalance() / 282);
                balanceResponse.setBalanceArs(account.getBalance());
                accountBalanceList.add(balanceResponse);
            }

            if (account.getCurrency().equals(USD)) {
                balanceResponse.setBalanceUsd(account.getBalance() * 282);
                balanceResponse.setBalanceArs(account.getBalance());
                accountBalanceList.add(balanceResponse);
            }
        }
        return accountBalanceList;
    }

    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        List<Account> responseList = repository.findAccountsByUserId(userId);
        if (responseList.isEmpty())
            throw new NoSuchElementException("The user does not have accounts yet");
        return responseList;
    }

    @Override
    public List<AccountResponseDto> getAccountsUserById(Long idUser) {
        List<Account> account = repository.findAccountsByUserId(idUser);
        if (account.isEmpty())
            throw new NoSuchElementException(String.format("Account list is empty: %s", account));
        return mapper.entityList2DtoList(account);
    }

    private AccountCurrencyEnum specificTypeOfCurrency(String type) {
        if (EUR.name().equalsIgnoreCase(type))
            return EUR;
        else if (USD.name().equalsIgnoreCase(type))
            return USD;
        return ARS;
    }
}
