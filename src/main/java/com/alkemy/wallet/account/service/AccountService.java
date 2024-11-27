package com.alkemy.wallet.account.service;

import com.alkemy.wallet.account.dto.request.OpenAccountRequest;
import com.alkemy.wallet.account.dto.request.TransactionLimitModificationRequest;
import com.alkemy.wallet.account.dto.response.AccountDetailsResponse;
import com.alkemy.wallet.customer.domain.Customer;

import java.util.List;

public interface AccountService {
    void openAccount(OpenAccountRequest request);

    AccountDetailsResponse modifyTransactionLimit(TransactionLimitModificationRequest request);

    void deactivateAccount(Long accountNumber);

    List<AccountDetailsResponse> getCustomerAssociatedAccounts(Customer loggedCustomer);
}
