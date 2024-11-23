package com.alkemy.wallet.model.dto.response;

import java.time.LocalDate;

public class FixedTermDepositSimulationResponseDto {

    private LocalDate createdAt;
    private LocalDate closingDate;
    private Double amountInvested;
    private Double interestEarned;
    private Double totalEarned;
}
