package com.alkemy.wallet.model.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FixedTermDepositRequestDto {
    private Double amount;
    private Long accountId;
    private String closingDate;
}
