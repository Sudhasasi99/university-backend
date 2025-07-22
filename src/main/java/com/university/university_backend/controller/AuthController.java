package com.university.university_backend.controller;

import com.university.university_backend.service.AuthService;
import com.university.university_backend.dto.RegisterRequest;
import com.university.university_backend.dto.LoginRequest;
import com.university.university_backend.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "https://joyful-cajeta-f0bc16.netlify.app")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "✅ Registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
