package com.alkemy.wallet.auth;

public record LoginRequest(
        String email,
        String password
) {
}
