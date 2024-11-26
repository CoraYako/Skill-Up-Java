package com.alkemy.wallet.customer.service;

import com.alkemy.wallet.model.entity.User;
import com.alkemy.wallet.repository.IRoleRepository;
import com.alkemy.wallet.utils.CustomMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;

import static com.alkemy.wallet.authentication.RoleEnum.ADMIN;
import static com.alkemy.wallet.authentication.RoleEnum.USER;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;
    private final CustomMessageSource messageSource;

    @Override
    public Role saveNewRole(String roleName, User user) {
        Role role = new Role();

        if (roleName.equalsIgnoreCase(ADMIN.getSimpleRoleName())) {
            role.setName(ADMIN);
            role.setUser(user);
        } else if (roleName.equalsIgnoreCase(USER.getSimpleRoleName())) {
            role.setName(USER);
            role.setUser(user);
        } else {
            throw new InputMismatchException(messageSource.message("role.mismatch", null));
        }

        roleRepository.save(role);
        return role;
    }
}
