package com.alkemy.wallet.service.impl;


import com.alkemy.wallet.model.dto.request.AccountRequestDto;
import com.alkemy.wallet.model.dto.response.AccountBalanceDto;
import com.alkemy.wallet.model.dto.response.AccountResponseDto;
import com.alkemy.wallet.model.entity.Account;
import com.alkemy.wallet.model.entity.AccountCurrencyEnum;
import com.alkemy.wallet.model.entity.FixedTermDeposit;
import com.alkemy.wallet.model.mapper.AccountMapper;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;
    private final ITransactionRepository iTransactionRepository;
    private final IUserRepository userRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponseDto save(AccountRequestDto request) {
        return null;
    }

    //TODO as the user comes with the id in the parameters, use the AuthService and verify if the id is the same as the logged user
    public AccountBalanceDto getAccountBalance(long idUser) {
        Optional<Account> account = accountRepository.findByUserId(idUser);
        if (account.isEmpty())
            return null;

        AccountBalanceDto accountBalanceDTO = new AccountBalanceDto();
        if (account.get().getCurrency().equals(AccountCurrencyEnum.ARS)) {
            accountBalanceDTO.setBalanceUSD(account.get().getBalance() / 282);
            accountBalanceDTO.setBalanceARS(account.get().getBalance());
        }

        if (account.get().getCurrency().equals(AccountCurrencyEnum.USD)) {
            accountBalanceDTO.setBalanceUSD(account.get().getBalance() * 282);
            accountBalanceDTO.setBalanceARS(account.get().getBalance());
        }

        LocalDate dateDB = LocalDate.of
                (account.get().getCreationDate().getYear(),
                account.get().getCreationDate().getMonth(),
                account.get().getCreationDate().getDayOfWeek().getValue());

        Period duration = Period.between(dateDB, LocalDate.now());
        if (duration.getMonths() > 0){
            accountBalanceDTO.setFixedTermDeposit(account.get().getBalance() * (282 * duration.getMonths()));
        }

        return accountBalanceDTO;
    }

    public AccountResponseDto createAccount(AccountResponseDto dto){
        Account account = new Account();
        account.setId(dto.getId());
        account.setBalance(0.0);
        account.setCreationDate(LocalDateTime.now());
        account.setCurrency(AccountCurrencyEnum.valueOf(dto.getCurrency()));
        account.setSoftDelete(Boolean.FALSE);
        account.setUpdateDate(dto.getUpdatedAt());


        account.setUser(userRepository.findById(
                dto.getUserId()).orElseThrow(
                        ()->new RuntimeException("User not found")));

        if (Objects.equals(dto.getCurrency(), "ARS")){
            account.setTransactionLimit(300000.00);
        }
        else if(Objects.equals(dto.getCurrency(), "USD")){
            account.setTransactionLimit(1000.00);
        }
        else{
            throw new RuntimeException("Currency must be ARS or USD");
        }


        accountRepository.save(account);


        return accountMapper.entity2Dto(account);
    }


}
