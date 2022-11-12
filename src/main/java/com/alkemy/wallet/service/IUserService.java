package com.alkemy.wallet.service;

import com.alkemy.wallet.model.dto.request.UserRequestDto;
import com.alkemy.wallet.model.dto.response.UserResponseDto;
import com.alkemy.wallet.model.entity.Account;
import com.alkemy.wallet.model.entity.User;

import java.util.List;

public interface IUserService {

    UserResponseDto update(Long id, String token, UserRequestDto request);

    User getEntityById(Long id);

    UserResponseDto getUserDetails(Long id, String token);

    void addAccount(User user, Account account);

    List<UserResponseDto> getUsers();

    void deleteUserById(Long id, String token);
}