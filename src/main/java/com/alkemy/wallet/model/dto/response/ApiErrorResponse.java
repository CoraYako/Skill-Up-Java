package com.alkemy.wallet.model.dto.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorResponse {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private List<String> errors;
}
