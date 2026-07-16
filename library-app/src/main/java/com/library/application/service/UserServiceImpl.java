package com.library.application.service;

import com.library.application.repository.UserRepository;
import com.library.domain.User;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(UUID id, User user) {
        User foundUser = getUserById(id);
        foundUser.setFullName(user.getFullName());
        return userRepository.save(foundUser);
    }

    @Override
    public void deleteUserById(UUID id) {
        User userToDelete = getUserById(id);
        userRepository.delete(userToDelete);
    }
}
