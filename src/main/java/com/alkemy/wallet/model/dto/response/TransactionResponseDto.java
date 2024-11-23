package com.alkemy.wallet.model.dto.response;

import java.time.LocalDateTime;

public class TransactionResponseDto {
    private Long id;
    private Double amount;
    private String type;
    private String description;
    private Long userId;
    private Long accountId;
    private LocalDateTime transactionDate;
}
