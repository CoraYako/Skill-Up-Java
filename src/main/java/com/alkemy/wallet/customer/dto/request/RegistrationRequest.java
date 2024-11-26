package com.alkemy.wallet.customer.dto.request;

public record RegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
