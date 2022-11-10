package com.alkemy.wallet.controller;

import com.alkemy.wallet.model.dto.response.AccountResponseDto;
import com.alkemy.wallet.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService service;

    @PostMapping
    public ResponseEntity<AccountResponseDto> createAccount(@RequestBody AccountResponseDto dto){
        return new ResponseEntity<>(service.createAccount(dto), HttpStatus.CREATED);
    }
}
