package com.alkemy.wallet.model.exception;

public class InvalidDaysRange extends RuntimeException {
    public InvalidDaysRange(String errorMessage) {
        super(errorMessage);
    }
}
