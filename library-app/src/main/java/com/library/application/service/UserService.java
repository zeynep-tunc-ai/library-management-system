package com.library.application.service;

import com.library.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User addUser(User user);
    User getUserById(UUID id);
    List<User> getAllUsers();
    User updateUser(UUID id, User user);
    void deleteUserById(UUID id);
}
