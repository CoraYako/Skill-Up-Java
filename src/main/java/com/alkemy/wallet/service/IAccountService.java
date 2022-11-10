package com.alkemy.wallet.service;

import com.alkemy.wallet.model.dto.request.AccountRequestDto;
import com.alkemy.wallet.model.dto.response.AccountBalanceResponseDto;
import com.alkemy.wallet.model.dto.response.AccountResponseDto;
import com.alkemy.wallet.model.entity.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {

    AccountResponseDto save(AccountRequestDto request);

    void save(Account account);

    List<AccountBalanceResponseDto> getAccountBalance(String token);

    List<AccountResponseDto> getAccountUserById(long idUser);

    Optional<Account> findTopByUserId(Long userId);
}
