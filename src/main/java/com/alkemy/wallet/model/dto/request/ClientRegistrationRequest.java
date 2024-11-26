package com.alkemy.wallet.model.dto.request;

public record ClientRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
