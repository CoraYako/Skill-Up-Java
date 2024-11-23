package com.alkemy.wallet.model.dto.response;

import java.time.LocalDateTime;

public class AccountResponseDto {
    private Long id;
    private String currency;
    private Double transactionLimit;
    private Double balance;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
