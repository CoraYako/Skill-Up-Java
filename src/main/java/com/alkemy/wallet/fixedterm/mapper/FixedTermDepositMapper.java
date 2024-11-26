package com.alkemy.wallet.fixedterm.mapper;

import com.alkemy.wallet.fixedterm.domain.FixedTermDeposit;
import com.alkemy.wallet.fixedterm.dto.response.FixedDepositDetailsResponse;
import org.springframework.stereotype.Component;

@Component
public class FixedTermDepositMapper {
    public FixedDepositDetailsResponse toDto(FixedTermDeposit fixedTermDeposit) {
        return new FixedDepositDetailsResponse(
                fixedTermDeposit.getId(),
                fixedTermDeposit.getInvestmentAmount().toString(),
                fixedTermDeposit.getSourceAccount().getAccountNumber(),
                fixedTermDeposit.getInterestEarned().toString(),
                fixedTermDeposit.getStartDate().toLocalDate(),
                fixedTermDeposit.getEndDate().toLocalDate()
        );
    }
}