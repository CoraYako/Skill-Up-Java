package com.alkemy.wallet.account.domain.exception;

public class InvalidAccountCurrencyType extends RuntimeException {
    public InvalidAccountCurrencyType(String errorMessage) {
        super(errorMessage);
    }
}
