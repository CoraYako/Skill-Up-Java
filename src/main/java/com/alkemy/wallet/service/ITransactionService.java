package com.alkemy.wallet.service;

import com.alkemy.wallet.model.entity.Transaction;

public interface ITransactionService {

    void save(Transaction transaction);

    String sendMoney(long idTargetUser, double amount, String money, int typeMoney, String type, String token);
}
