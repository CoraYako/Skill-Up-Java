package com.alkemy.wallet.controller;

import com.alkemy.wallet.model.dto.response.AccountBalanceResponseDto;
import com.alkemy.wallet.model.dto.response.AccountResponseDto;
import com.alkemy.wallet.service.IAccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountControllerTest {
    private final IAccountService service = Mockito.mock(IAccountService.class);
    private final AccountController controller = new AccountController(service);

    @Test
    void getAccountBalance() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhcnJveW9sb2t1cmFAZ21haWwuY29tIiwicm9sZSI6IkFETUlOIiwiZXhwIjoxNjY4MDMxNjk4LCJpYXQiOjE2Njc5OTU2OTh9.tJ_rksi7lC4vldQPbiK83bntFgSIcgxKEz8u3OnBv-U";
        Mockito.when(service.getAccountBalance(token)).thenReturn(List.of(new AccountBalanceResponseDto(100D, 6.25, 30D)));

        ResponseEntity<List<AccountBalanceResponseDto>> response = controller.getAccountBalance(token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new ResponseEntity<>(List.of(new AccountBalanceResponseDto(100D, 6.25, 30D)), HttpStatus.OK), response);
    }

    @Test
    void getAccountUserById() {
        LocalDateTime fecha = LocalDateTime.now();
        Mockito.when(service.getAccountUserById(2)).thenReturn(List.of(new AccountResponseDto(2L, "ARS", 1500D, 3000D, 1L, fecha, null)));
        ResponseEntity<List<AccountResponseDto>> response = controller.getAccountUserById(2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new ResponseEntity<>(List.of(new AccountResponseDto(2L, "ARS", 1500D, 3000D, 1L, fecha, null)), HttpStatus.OK), response);
    }
}