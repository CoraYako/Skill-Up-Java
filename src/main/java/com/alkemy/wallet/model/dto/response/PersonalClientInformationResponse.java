package com.alkemy.wallet.model.dto.response;

import java.time.LocalDate;

public record PersonalClientInformationResponse(
        Long id,
        String fullName,
        String email,
        LocalDate registrationDate,
        LocalDate lastModification
) {
}
