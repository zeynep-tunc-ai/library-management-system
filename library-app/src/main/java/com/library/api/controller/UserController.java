package com.library.api.controller;

import com.library.application.service.UserService;
import com.library.domain.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/com/library/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User savedUser = userService.addUser(user);
        return ResponseEntity.ok(savedUser);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id,@Valid @RequestBody User user){
        User updateUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updateUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserBYId(@PathVariable UUID id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
