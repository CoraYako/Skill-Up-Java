package com.alkemy.wallet.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AccountBalanceResponseDto {
    private Double balanceARS;
    private Double balanceUSD;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Double fixedTermDepositARS;
    private Double fixedTermDepositUSD;

}
