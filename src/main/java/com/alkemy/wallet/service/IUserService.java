package com.alkemy.wallet.service;

import com.alkemy.wallet.model.dto.response.UserResponseDto;
import com.alkemy.wallet.model.dto.response.list.UserListResponseDto;
import com.alkemy.wallet.model.entity.User;

import java.util.Optional;

public interface IUserService {

    UserResponseDto getUserById(Long id);

    Optional<User> getByUserId(Long id);

    UserListResponseDto getUsers();
}
