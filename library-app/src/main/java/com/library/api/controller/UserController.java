package com.library.api.controller;

import com.library.application.service.UserService;
import com.library.domain.User;
import com.library.dto.CreateUserRequest;
import com.library.dto.UpdateUserRequest;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/com/library/api/users")
@CrossOrigin(origins = "**")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody CreateUserRequest request){
        User savedUser = userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id,@Valid @RequestBody UpdateUserRequest request){
        User updateUser = userService.updateUser(id, request);
        return ResponseEntity.ok(updateUser);
    }
    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable){
        Page<User> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserBYId(@PathVariable UUID id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
