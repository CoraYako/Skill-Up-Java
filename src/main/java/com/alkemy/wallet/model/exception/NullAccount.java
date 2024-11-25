package com.alkemy.wallet.model.exception;

public class NullAccount extends RuntimeException {
    public NullAccount(String errorMessage) {
        super(errorMessage);
    }
}
