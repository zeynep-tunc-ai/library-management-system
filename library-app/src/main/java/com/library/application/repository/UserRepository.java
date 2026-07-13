package com.library.application.repository;

import com.library.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    void save(User user);
    void delete(User user);
    User findById(UUID id);
    List<User> findAll();
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
