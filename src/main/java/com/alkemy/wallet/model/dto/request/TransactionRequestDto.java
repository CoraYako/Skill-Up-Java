package com.alkemy.wallet.model.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.alkemy.wallet.utils.TransactionUtil.MIN_AMOUNT_TO_SEND;

public class TransactionRequestDto {
    @Min(value = MIN_AMOUNT_TO_SEND, message = "{transaction.invalid.min-amount}")
    @NotNull(message = "{transaction.invalid.min-amount}")
    private Double amount;
    private String description;
    @NotNull(message = "{transaction.invalid-account}")
    private Long accountId;
}
