package com.alkemy.wallet.service;

import com.alkemy.wallet.model.dto.request.FixedTermDepositRequestDto;
import com.alkemy.wallet.model.dto.response.FixedTermDepositResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface IFixedTermDepositService {
     FixedTermDepositResponseDto save(FixedTermDepositRequestDto requestDto, String token);
     List<String> simulateFixedTerm(Double amount, LocalDate dateOfEnd);

}
