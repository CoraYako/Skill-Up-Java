package com.alkemy.wallet.account.service;

import com.alkemy.wallet.account.domain.Account;
import com.alkemy.wallet.model.dto.request.AccountRequestDto;
import com.alkemy.wallet.model.dto.request.UpdateAccountRequestDto;
import com.alkemy.wallet.model.dto.response.AccountBalanceResponseDto;
import com.alkemy.wallet.model.dto.response.AccountResponseDto;
import com.alkemy.wallet.model.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {

    AccountResponseDto createNewAccount(AccountRequestDto accountRequestDto);

    List<Account> createDefaultAccounts(User user);

    AccountResponseDto updateAccount(Long id, UpdateAccountRequestDto updateAccountRequestDto);

    void editBalanceAndSave(Account account, Double newBalance);

    AccountBalanceResponseDto getAccountBalance();

    Account getAccountByCurrencyAndUserId(String currency, Long userId);

    Account getAccountById(Long id);

    List<AccountResponseDto> getAccountListByUserId(Long userId);

    Page<AccountResponseDto> getAllAccounts(Integer pageNumber);
}
