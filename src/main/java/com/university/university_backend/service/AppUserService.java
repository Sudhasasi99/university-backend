package com.university.university_backend.service;

import com.university.university_backend.entity.AppUser;
import com.university.university_backend.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> getUserById(Long id) {
        return appUserRepository.findById(id);
    }

    public AppUser createUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public AppUser updateUser(Long id, AppUser userDetails) {
        return appUserRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            user.setStatus(userDetails.getStatus());
            return appUserRepository.save(user);
        }).orElse(null);
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }
} 