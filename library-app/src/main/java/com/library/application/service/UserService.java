package com.library.application.service;

import com.library.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User addUser(User user);
    User getUserById(UUID id);
    List<User> getAllUsers();
    User updateUser(UUID id, User user);
    void deleteUserById(UUID id);
    Page<User> getAllUsersPaged(Pageable pageable);
}
