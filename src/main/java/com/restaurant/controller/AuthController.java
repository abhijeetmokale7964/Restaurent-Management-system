package com.restaurant.controller;

import com.restaurant.dto.AuthResponse;
import com.restaurant.dto.LoginRequest;
import com.restaurant.entity.User;
import com.restaurant.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // REGISTER USER
    @PostMapping("/register")
    public String registerUser(@RequestBody User user){

        userService.register(user);

        return "User Registered Successfully";
    }

 // LOGIN USER
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        return userService.login(
                request.getEmail(),
                request.getPassword()
        );
    }}