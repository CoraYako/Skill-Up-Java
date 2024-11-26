package com.alkemy.wallet.fixedterm.dto.request;

public record CreateFixedTermRequest(
        String investmentAmount,
        Long debitAccountNumber,
        int termLengthInDays
) {
}
