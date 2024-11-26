package com.alkemy.wallet.authentication.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
