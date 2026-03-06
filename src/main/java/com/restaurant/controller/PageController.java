package com.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(){
        return "admin-dashboard";
    }

    @GetMapping("/staff/dashboard")
    public String staffDashboard(){
        return "staff-dashboard";
    }
}