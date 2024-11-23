package com.alkemy.wallet.model.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class AccountRequestDto {
    @NotEmpty(message = "{account.invalid-currency}")
    @NotBlank(message = "{account.invalid-currency}")
    private String currency;
}
