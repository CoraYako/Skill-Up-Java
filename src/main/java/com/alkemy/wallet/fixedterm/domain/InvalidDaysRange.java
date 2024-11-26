package com.alkemy.wallet.fixedterm.domain;

public class InvalidDaysRange extends RuntimeException {
    public InvalidDaysRange(String errorMessage) {
        super(errorMessage);
    }
}
