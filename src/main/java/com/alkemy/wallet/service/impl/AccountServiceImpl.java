package com.alkemy.wallet.service.impl;


import com.alkemy.wallet.controller.exception.Mistake;
import com.alkemy.wallet.model.dto.request.AccountRequestDto;
import com.alkemy.wallet.model.dto.response.AccountBalanceResponseDto;
import com.alkemy.wallet.model.dto.response.AccountResponseDto;
import com.alkemy.wallet.model.entity.Account;
import com.alkemy.wallet.model.entity.AccountCurrencyEnum;
import com.alkemy.wallet.model.mapper.AccountMapper;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final IAuthService authService;

    @Override
    public AccountResponseDto save(AccountRequestDto request) {
        return null;
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> findTopByUserId(Long userId) {
        return accountRepository.findTopByUserId(userId);
    }

    @Override
    public List<AccountResponseDto> getAccountUserById(long idUser) {
        List<Account> account = accountRepository.findAccountByUserId(idUser);
        if (account.isEmpty())
            return Collections.emptyList();
        return accountMapper.entityList2DtoList(account);
    }

    @Override
    public List<AccountBalanceResponseDto> getAccountBalance(String token) {
        long idUser = authService.getUserFromToken(token).getId();
        List<Account> account = accountRepository.findAccountByUserId(idUser);
        if (account.isEmpty())
            throw new Mistake("Usuario no disponible");

        List<AccountBalanceResponseDto> accountBalanceList = new ArrayList<>();

        for (Account value : account) {
            AccountBalanceResponseDto accountBalanceResponseDTO;

            LocalDate dateDB = LocalDate.of
                    (value.getCreationDate().getYear(),
                            value.getCreationDate().getMonth(),
                            value.getCreationDate().getDayOfWeek().getValue());

            Period duration = Period.between(dateDB, LocalDate.now());
            accountBalanceResponseDTO = new AccountBalanceResponseDto();
            if (duration.getMonths() > 0) {
                accountBalanceResponseDTO.setFixedTermDeposit(value.getBalance() * (282 * duration.getMonths()));
            }
            if (value.getCurrency().equals(AccountCurrencyEnum.ARS)) {
                accountBalanceResponseDTO.setBalanceUsd(value.getBalance() / 282);
                accountBalanceResponseDTO.setBalanceArs(value.getBalance());
                accountBalanceList.add(accountBalanceResponseDTO);
            }

            if (value.getCurrency().equals(AccountCurrencyEnum.USD)) {
                accountBalanceResponseDTO.setBalanceUsd(value.getBalance() * 282);
                accountBalanceResponseDTO.setBalanceArs(value.getBalance());
                accountBalanceList.add(accountBalanceResponseDTO);
            }
        }
        return accountBalanceList;
    }
}
