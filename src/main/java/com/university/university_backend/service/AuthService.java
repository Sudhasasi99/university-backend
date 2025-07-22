package com.university.university_backend.service;

import com.university.university_backend.dto.*;
import com.university.university_backend.entity.AppUser;
import com.university.university_backend.repository.AppUserRepository;
import com.university.university_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired private AppUserRepository appUserRepo;
    @Autowired private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void register(RegisterRequest request) {
        AppUser user = new AppUser();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole()); // Make sure RegisterRequest has a role field
        user.setStatus("Active");
        appUserRepo.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        AppUser user = appUserRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getRole(), user.getName());
    }
}
