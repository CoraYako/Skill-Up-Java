package com.alkemy.wallet.customer.mapper;

import com.alkemy.wallet.customer.domain.Customer;
import com.alkemy.wallet.customer.dto.request.RegistrationRequest;
import com.alkemy.wallet.customer.dto.response.ProfileDetailsResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toEntity(RegistrationRequest request) {
        return new Customer(
                request.firstName(),
                request.lastName(),
                request.email()
        );
    }

    public ProfileDetailsResponse toDto(Customer customer) {
        return new ProfileDetailsResponse(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getRegistrationDate().toLocalDate(),
                customer.getLastProfileModificationDate().toLocalDate());
    }
}