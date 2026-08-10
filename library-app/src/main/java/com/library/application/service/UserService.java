package com.library.application.service;

import com.library.domain.User;
import com.library.dto.CreateUserRequest;
import com.library.dto.UpdateUserRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    User addUser(@Valid CreateUserRequest request);
    User getUserById(UUID id);
    User updateUser(UUID id, UpdateUserRequest request);
    void deleteUserById(UUID id);
    Page<User> getAllUsers(Pageable pageable);
}
