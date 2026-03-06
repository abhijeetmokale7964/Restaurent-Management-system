package com.restaurant.controller;

import com.restaurant.entity.User;
import com.restaurant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserRepository userRepository;

    // GET ALL REGISTERED USERS
    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}