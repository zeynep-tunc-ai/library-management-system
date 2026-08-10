package com.library.application.service;

import com.library.application.repository.UserRepository;
import com.library.domain.Role;
import com.library.domain.User;
import com.library.dto.CreateUserRequest;
import com.library.dto.UpdateUserRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User addUser(@Valid CreateUserRequest request) {
        //DTO'dan gelen verileri aktarmak için yeni bir User nesnesi oluşturur
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setIdentityNumber(request.getIdentityNumber());
        //Parolayı metin olarak değil, BCrypt ile şifreleyerek kaydeder
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // Role null gelirse varsayılan olarak ROLE_USER atar
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        } else {
            user.setRole(Role.ROLE_USER);
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(UUID id, UpdateUserRequest request) {
        User foundUser = getUserById(id);

        if (request.getUsername() != null) {
            foundUser.setUsername(request.getUsername());
        }
        if (request.getPassword() != null) {
            foundUser.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return userRepository.save(foundUser);
    }

    @Override
    public void deleteUserById(UUID id) {
        User userToDelete = getUserById(id);
        userRepository.delete(userToDelete);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
