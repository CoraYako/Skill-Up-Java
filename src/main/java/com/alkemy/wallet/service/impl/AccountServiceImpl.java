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

import java.text.DecimalFormat;
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
        double valorPesoArs = 160.41;
        double valorPesoUsd = 0.0062;
        double porcentaje = 10;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        long idUser = authService.getUserFromToken(token).getId();
        List<Account> accounts = accountRepository.findAccountByUserId(idUser);
        if (accounts.isEmpty())
            throw new Mistake("Usuario no disponible");

        List<AccountBalanceResponseDto> accountBalanceList = new ArrayList<>();

        for (Account account : accounts) {
            AccountBalanceResponseDto accountBalanceResponseDTO;

            LocalDate dateDB = LocalDate.of
                    (account.getCreationDate().getYear(),
                            account.getCreationDate().getMonth(),
                            account.getCreationDate().getDayOfWeek().getValue());

            Period duration = Period.between(dateDB, LocalDate.now());
            accountBalanceResponseDTO = new AccountBalanceResponseDto();
            if (duration.getMonths() > 0) {
                double aumento = (account.getBalance() * porcentaje) / 100;
                accountBalanceResponseDTO.setFixedTermDeposit(account.getBalance() + (aumento * duration.getMonths()));
            }
            if (account.getCurrency().equals(AccountCurrencyEnum.ARS)) {
                double arsToUsd = Double.parseDouble(decimalFormat.format((account.getBalance() * valorPesoArs)));
                accountBalanceResponseDTO.setBalanceUsd(arsToUsd);
                accountBalanceResponseDTO.setBalanceArs(account.getBalance());
                accountBalanceList.add(accountBalanceResponseDTO);
            } else if (account.getCurrency().equals(AccountCurrencyEnum.USD)) {
                double usdToArs = Double.parseDouble(decimalFormat.format((account.getBalance() * valorPesoUsd)));
                accountBalanceResponseDTO.setBalanceUsd(account.getBalance());
                accountBalanceResponseDTO.setBalanceArs(usdToArs);
                accountBalanceList.add(accountBalanceResponseDTO);
            }
        }
        return accountBalanceList;
    }
}
