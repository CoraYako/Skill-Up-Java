package com.alkemy.wallet.controller;

import com.alkemy.wallet.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    @PostMapping("/sendArs")
    public ResponseEntity<String> moneySendInPesos(@RequestParam("idTargetUser") Long idTargetUser, @RequestParam("mount") Double amount,
                                                   @RequestParam("type") String type, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(transactionService.sendMoney(idTargetUser, amount, "peso Argentino(ARS)", 1, type, token), HttpStatus.ACCEPTED);
    }

    @PostMapping("/sendUsd")
    public ResponseEntity<String> moneySendInUsd(@RequestParam("idTargetUser") Long idTargetUser, @RequestParam("mount") Double amount,
                                                 @RequestParam("type") String type, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(transactionService.sendMoney(idTargetUser, amount, "dolar Estadounidense(USD)", 2, type, token), HttpStatus.ACCEPTED);

    }
}
