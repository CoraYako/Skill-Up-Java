package com.alkemy.wallet.customer.dto.request;

public record CredentialsModificationRequest(
        String email,
        String password
) {
}
