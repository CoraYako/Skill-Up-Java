package com.alkemy.wallet.account.domain.exception;

public class NullAccount extends RuntimeException {
    public NullAccount(String errorMessage) {
        super(errorMessage);
    }
}
