package com.alkemy.wallet.service;

public interface ITransactionService {
    String deposit(Double toDeposit, Long accountId, String description);
}
