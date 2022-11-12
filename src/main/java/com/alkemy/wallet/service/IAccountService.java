package com.alkemy.wallet.service;

import com.alkemy.wallet.model.dto.request.AccountRequestDto;
import com.alkemy.wallet.model.dto.response.AccountBalanceResponseDto;
import com.alkemy.wallet.model.dto.response.AccountResponseDto;
import com.alkemy.wallet.model.entity.Account;
import com.alkemy.wallet.model.entity.User;

import java.util.List;

public interface IAccountService {

    Account getAccountById(long id);

    AccountResponseDto save(AccountRequestDto request, String token);

    List<Account> createUserAccounts(User user);

    Account getByCurrencyAndUserId(String currency, Long userId);

    void editBalanceAndSave(Account account, Double newBalance);

    List<AccountBalanceResponseDto> getAccountBalance(String token);

    List<Account> getAccountsByUserId(Long userId);

    List<AccountResponseDto> getAccountsUserById(Long idUser);
}
