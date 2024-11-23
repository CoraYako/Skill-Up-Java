package com.alkemy.wallet.model.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<String> authorities;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
