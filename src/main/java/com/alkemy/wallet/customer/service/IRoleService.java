package com.alkemy.wallet.customer.service;

import com.alkemy.wallet.model.entity.User;

public interface IRoleService {

    Role saveNewRole(String roleName, User user);
}
