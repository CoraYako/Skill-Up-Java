package com.alkemy.wallet.auth;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ApiErrorResponse(
        HttpStatus status,
        LocalDateTime timestamp,
        List<String> errorsStack
) {
}
