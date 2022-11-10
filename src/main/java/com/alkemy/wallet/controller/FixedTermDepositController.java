package com.alkemy.wallet.controller;

import com.alkemy.wallet.model.dto.request.FixedTermDepositRequestDto;
import com.alkemy.wallet.model.dto.response.FixedTermDepositResponseDto;
import com.alkemy.wallet.service.IFixedTermDepositService;
import com.alkemy.wallet.service.impl.FixedTermDepositServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/fixedDeposit")
public class FixedTermDepositController {
    private final IFixedTermDepositService fixedTermDepositService;

    public FixedTermDepositController(FixedTermDepositServiceImpl fixedTermDepositServiceImpl) {
        this.fixedTermDepositService = fixedTermDepositServiceImpl;
    }

    @PostMapping
    public ResponseEntity<FixedTermDepositResponseDto> save(@RequestBody FixedTermDepositRequestDto requestDto,
                                                            @RequestHeader String  token) {
        FixedTermDepositResponseDto response = fixedTermDepositService.save(requestDto, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/simulate/{amount}/{futureDate}")
    public ResponseEntity<?> simulate(@PathVariable Double amount,
                                      @PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate futureDate){
        return new ResponseEntity<>(fixedTermDepositService.simulateFixedTerm(amount,futureDate), HttpStatus.OK);
    }


}
