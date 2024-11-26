package com.alkemy.wallet.customer.dto.response;

import java.time.LocalDate;

public record ProfileDetailsResponse(
        Long id,
        String fullName,
        String email,
        LocalDate registrationDate,
        LocalDate lastModification
) {
}
