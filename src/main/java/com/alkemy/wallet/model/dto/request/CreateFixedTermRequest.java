package com.alkemy.wallet.model.dto.request;

public record CreateFixedTermRequest(
        String investmentAmount,
        Long debitAccountNumber,
        int termLengthInDays
) {
}
