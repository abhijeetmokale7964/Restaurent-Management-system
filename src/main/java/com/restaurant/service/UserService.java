package com.restaurant.service;


import com.restaurant.dto.AuthResponse;
import com.restaurant.entity.User;

public interface UserService {

    User register(User user);

    AuthResponse login(String email, String password);

}