package com.alkemy.wallet.controller;

import com.alkemy.wallet.model.dto.response.AccountBalanceDto;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final IAccountService accountBalanceService;
    private final ITransactionService iTransactionService;

    @GetMapping("/account/balance/{idUser}")
    public ResponseEntity<AccountBalanceDto> getAccountBalance(@PathVariable("idUser") Long idUser){
        return new ResponseEntity<>(accountBalanceService.getAccountBalance(idUser), HttpStatus.OK);

    }
    @PostMapping
    @RequestMapping("/deposit/{amount}/{account_id}/{description}")
    public ResponseEntity<?> deposit(@PathVariable Double amount,
                                     @PathVariable Long account_id,
                                     @PathVariable String description){
        return new ResponseEntity<>(iTransactionService.deposit(amount,account_id, description), HttpStatus.OK);
    }
}
