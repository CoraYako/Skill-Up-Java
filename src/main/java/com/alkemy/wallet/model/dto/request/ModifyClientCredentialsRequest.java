package com.alkemy.wallet.model.dto.request;

public record ModifyClientCredentialsRequest(
        String email,
        String password
) {
}
