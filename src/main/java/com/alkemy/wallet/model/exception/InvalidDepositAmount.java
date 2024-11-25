package com.alkemy.wallet.model.exception;

public class InvalidDepositAmount extends RuntimeException {
    public InvalidDepositAmount(String errorMessage) {
        super(errorMessage);
    }
}
