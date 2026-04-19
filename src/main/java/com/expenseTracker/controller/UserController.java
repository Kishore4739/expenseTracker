package com.expenseTracker.controller;

import com.expenseTracker.dto.UserRequest;
import com.expenseTracker.dto.UserResponse;
import com.expenseTracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER USER
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody UserRequest request
    ) {
        UserResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }
}
