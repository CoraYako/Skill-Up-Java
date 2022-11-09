package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.entity.Account;
import com.alkemy.wallet.model.entity.Transaction;
import com.alkemy.wallet.model.entity.TransactionTypeEnum;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(IAccountRepository accountRepository,
                                  ITransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;

    }

    public String deposit(Double toDeposit, Long accountId, String description){
        if(toDeposit >0.01) {
            Account account = accountRepository.findById(accountId).orElseThrow(
                    () -> new RuntimeException("Account not found"));

            Double actualBalance = account.getBalance();
            Double newBalance = actualBalance + toDeposit;
            account.setBalance(newBalance);

            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setAccount(account);
            transaction.setTransactionDate(ZonedDateTime.now().toLocalDateTime());
            transaction.setAmount(toDeposit);
            transaction.setType(TransactionTypeEnum.DEPOSIT);
            transaction.setUser(account.getUser());
            transaction.setUser(account.getUser());
            transaction.setDescription(description);

            accountRepository.save(account);
            transactionRepository.save(transaction);

            return ("Transaction number: " + transaction.getId() +
                    " has been successful. Actual balance: " + newBalance);
        }

        else{

            return("Deposit value must be greater than $0.01");

        }
    }
}
