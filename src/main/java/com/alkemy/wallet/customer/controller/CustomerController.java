package com.alkemy.wallet.customer.controller;

import com.alkemy.wallet.customer.service.CustomerService;
import com.alkemy.wallet.model.dto.request.UserUpdateRequestDto;
import com.alkemy.wallet.model.dto.response.UserResponseDto;
import com.alkemy.wallet.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/users")
public class CustomerController {
    private final CustomerService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserDetails(@AuthenticationPrincipal User loggedUser, @PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(userService.getUserDetails(id, loggedUser));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id,
                                                      @AuthenticationPrincipal User loggedUser,
                                                      @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return ResponseEntity.status(OK).body(userService.updateUser(id, loggedUser, userUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id, @AuthenticationPrincipal User loggedUser) {
        userService.deleteUserById(id, loggedUser);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(@RequestParam(name = "page") Integer pageNumber) {
        return ResponseEntity.status(OK).body(userService.getAllUsers(pageNumber));
    }
}
