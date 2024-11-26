package com.alkemy.wallet.customer.service;

import com.alkemy.wallet.customer.domain.Customer;
import com.alkemy.wallet.customer.dto.request.CredentialsModificationRequest;
import com.alkemy.wallet.customer.dto.request.RegistrationRequest;
import com.alkemy.wallet.customer.dto.response.ProfileDetailsResponse;

public interface CustomerService {
    void customerRegistration(RegistrationRequest registrationRequest);

    ProfileDetailsResponse updateCustomerCredentials(Customer loggedCustomer, CredentialsModificationRequest request);

    void deactivateCustomerProfile(Customer loggedCustomer);

    ProfileDetailsResponse getCustomerProfileDetails(Customer loggedCustomer);
}