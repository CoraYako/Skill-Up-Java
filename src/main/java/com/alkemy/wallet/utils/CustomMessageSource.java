package com.alkemy.wallet.utils;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import static java.util.Locale.US;

@Component
public class CustomMessageSource {

    private final MessageSource messageSource;

    public String message(String code, Object[] args) {
        return messageSource.getMessage(code, args, US);
    }
}
