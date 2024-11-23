package com.alkemy.wallet.model.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FixedTermDepositResponseDto {
    private Long id;
    private Double amount;
    private Long userId;
    private Long accountId;
    private Double interest;
    private LocalDateTime createdAt;
    private LocalDate closingDate;
}
