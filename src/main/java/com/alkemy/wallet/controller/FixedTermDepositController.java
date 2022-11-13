package com.alkemy.wallet.controller;

import com.alkemy.wallet.model.dto.request.FixedTermDepositRequestDto;
import com.alkemy.wallet.model.dto.response.FixedTermDepositResponseDto;
import com.alkemy.wallet.service.IFixedTermDepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/fixedDeposit")
public class FixedTermDepositController {
    private final IFixedTermDepositService service;

    @PostMapping
    public ResponseEntity<FixedTermDepositResponseDto> createFixedTermDeposit(@RequestBody FixedTermDepositRequestDto requestDto, @RequestHeader("Authorization") String  token) {
        FixedTermDepositResponseDto response = service.create(requestDto, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
