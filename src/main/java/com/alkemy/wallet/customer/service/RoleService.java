package com.alkemy.wallet.customer.service;

import com.alkemy.wallet.model.entity.User;

public interface RoleService {

    Role saveNewRole(String roleName, User user);
}
