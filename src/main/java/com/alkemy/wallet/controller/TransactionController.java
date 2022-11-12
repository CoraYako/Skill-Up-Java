package com.alkemy.wallet.controller;

import com.alkemy.wallet.model.dto.request.TransactionRequestDto;
import com.alkemy.wallet.model.dto.response.TransactionResponseDto;
import com.alkemy.wallet.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.alkemy.wallet.model.entity.AccountCurrencyEnum.ARS;
import static com.alkemy.wallet.model.entity.AccountCurrencyEnum.USD;
import static com.alkemy.wallet.model.entity.TransactionTypeEnum.DEPOSIT;
import static com.alkemy.wallet.model.entity.TransactionTypeEnum.PAYMENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService service;

    @PostMapping("/sendArs")
    public ResponseEntity<TransactionResponseDto> sendARS(@Validated @RequestBody TransactionRequestDto request, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.sendMoneyIndicatingCurrency(ARS.toString(), request, token), OK);
    }

    @PostMapping("/sendUsd")
    public ResponseEntity<TransactionResponseDto> sendUsd(@Validated @RequestBody TransactionRequestDto request, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.sendMoneyIndicatingCurrency(USD.toString(), request, token), OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponseDto> deposit(@Validated @RequestBody TransactionRequestDto request, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.deposit(request, token), OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<TransactionResponseDto> payment(@Validated @RequestBody TransactionRequestDto request, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.payment(request, token), OK);
    }
}
