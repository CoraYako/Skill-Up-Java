package com.alkemy.wallet.transaction_record;

public class TransactionNotFound extends RuntimeException {
    public TransactionNotFound(String errorMessage) {
        super(errorMessage);
    }
}
