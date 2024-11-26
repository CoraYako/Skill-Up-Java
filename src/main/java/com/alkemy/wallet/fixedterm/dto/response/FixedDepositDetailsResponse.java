package com.alkemy.wallet.fixedterm.dto.response;

import java.time.LocalDate;

public record FixedDepositDetailsResponse(
        Long id,
        String investedAmount,
        Long debitAccountNumber,
        String interestEarned,
        LocalDate startDate,
        LocalDate endDate
) {
}
