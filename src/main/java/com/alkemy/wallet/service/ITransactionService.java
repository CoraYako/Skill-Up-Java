package com.alkemy.wallet.service;

import com.alkemy.wallet.model.dto.request.TransactionRequestDto;
import com.alkemy.wallet.model.dto.response.TransactionResponseDto;

public interface ITransactionService {

    TransactionResponseDto sendMoneyIndicatingCurrency(String currency, TransactionRequestDto request, String token);

    TransactionResponseDto deposit(TransactionRequestDto request, String token);

    TransactionResponseDto payment(TransactionRequestDto request, String token);
}
