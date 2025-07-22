package com.university.university_backend.controller;

import com.university.university_backend.entity.AdmissionsRegistration;
import com.university.university_backend.repository.AdmissionsRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionsRegistrationController {
    @Autowired
    private AdmissionsRegistrationRepository admissionsRegistrationRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AdmissionsRegistration registration) {
        AdmissionsRegistration saved = admissionsRegistrationRepository.save(registration);
        return ResponseEntity.ok(saved);
    }
} 