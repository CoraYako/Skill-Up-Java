package com.alkemy.wallet.model.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class AuthRequestDto {
    @NotEmpty(message = "{user.invalid-email}")
    @NotBlank(message = "{user.invalid-email}")
    @Email(message = "{user.invalid-email}")
    private String email;

    @NotEmpty(message = "{user.invalid-password}")
    @NotBlank(message = "{user.invalid-password}")
    private String password;
}
