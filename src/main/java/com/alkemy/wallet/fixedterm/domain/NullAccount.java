package com.alkemy.wallet.fixedterm.domain;

public class NullAccount extends RuntimeException {
    public NullAccount(String errorMessage) {
        super(errorMessage);
    }
}
