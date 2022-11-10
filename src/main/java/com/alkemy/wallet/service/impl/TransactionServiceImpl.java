package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.controller.exception.Mistake;
import com.alkemy.wallet.model.entity.*;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.IAuthService;
import com.alkemy.wallet.service.ITransactionService;
import com.alkemy.wallet.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {

    private final ITransactionRepository repository;

    private final IUserService userService;

    private final IAuthService authService;

    private final IAccountService accountService;

    @Override
    public void save(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    public String sendMoney(long idTargetUser, double amount, String money, int typeMoney, String type, String token) {
        String noDisponible = " no esta disponible";
        long idUser = authService.getUserFromToken(token).getId();
        if (idTargetUser == idUser)
            throw new Mistake("Error no se puede enviar dinero al mismo usuario");

        Optional<User> user = userService.getByUserId(idUser);
        if (user.isEmpty())
            throw new Mistake("El usuario con id " + idUser + noDisponible);

        Optional<User> targetUser = userService.getByUserId(idTargetUser);
        if (targetUser.isEmpty())
            throw new Mistake("El usuario con id " + idTargetUser + noDisponible);

        Optional<Account> accountUser = accountService.findTopByUserId(idUser);
        if (accountUser.isEmpty())
            throw new Mistake("La account con id " + idUser + noDisponible);

        Optional<Account> accountTargetUser = accountService.findTopByUserId(idTargetUser);
        if (accountTargetUser.isEmpty())
            throw new Mistake("La account con id " + idTargetUser + noDisponible);

        validTypeOfMoney(typeMoney, money, accountUser.get(), accountTargetUser.get());

        if (accountUser.get().getBalance() < amount)
            throw new Mistake("Error valor disponible superado");

        if (amount > accountUser.get().getTransactionLimit())
            throw new Mistake("Error supera el limite de transacciones");

        double balanceUser = accountUser.get().getBalance() - amount;
        double targetUserBalance = accountTargetUser.get().getBalance() + amount;

        accountUser.get().setBalance((balanceUser));
        accountTargetUser.get().setBalance(targetUserBalance);

        Transaction transaction = new Transaction(null, amount, specificTypeOfTransaction(type), "Transacción exitosa",
                LocalDateTime.now(), targetUser.get(), accountUser.get());

        accountService.save(accountTargetUser.get());
        accountService.save(accountUser.get());
        repository.save(transaction);

        return "Operación realizada exitosamente";
    }

    private void validTypeOfMoney(int typeMoney, String money, Account accountUser, Account accountTargetUser) {
        String error = "Error solo puede enviar dinero en ";
        if (typeMoney == 1 && (!accountUser.getCurrency().equals(AccountCurrencyEnum.ARS) || !accountTargetUser.getCurrency().equals(AccountCurrencyEnum.ARS)))
            throw new Mistake(error + money);
        else {
            if (typeMoney == 2 && (!accountUser.getCurrency().equals(AccountCurrencyEnum.USD) || !accountTargetUser.getCurrency().equals(AccountCurrencyEnum.USD)))
                throw new Mistake(error + money);
        }
    }

    private TransactionTypeEnum specificTypeOfTransaction(String type) {
        if (TransactionTypeEnum.PAYMENT.name().equalsIgnoreCase(type))
            return TransactionTypeEnum.PAYMENT;
        else if (TransactionTypeEnum.INCOME.name().equalsIgnoreCase(type))
            return TransactionTypeEnum.INCOME;
        else if (TransactionTypeEnum.DEPOSIT.name().equalsIgnoreCase(type))
            return TransactionTypeEnum.DEPOSIT;
        else
            throw new Mistake("El tipo ingresado es incorrecto");
    }
}
