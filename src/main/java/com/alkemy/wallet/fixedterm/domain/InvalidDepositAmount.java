package com.alkemy.wallet.fixedterm.domain;

public class InvalidDepositAmount extends RuntimeException {
    public InvalidDepositAmount(String errorMessage) {
        super(errorMessage);
    }
}
