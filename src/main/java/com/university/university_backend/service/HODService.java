package com.university.university_backend.service;

import com.university.university_backend.entity.HOD;
import com.university.university_backend.repository.HODRepository;
import com.university.university_backend.entity.AppUser;
import com.university.university_backend.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HODService {
    @Autowired
    private HODRepository hodRepository;
    @Autowired
    private AppUserService appUserService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<HOD> getAllHODs() {
        return hodRepository.findAll();
    }

    public Optional<HOD> getHODById(Long id) {
        return hodRepository.findById(id);
    }

    public HOD createHOD(HOD hod) {
        HOD savedHOD = hodRepository.save(hod);
        // Also create AppUser for login
        if (hod.getEmail() != null && hod.getPassword() != null) {
            AppUser user = new AppUser();
            user.setName(hod.getName());
            user.setEmail(hod.getEmail());
            user.setPassword(passwordEncoder.encode(hod.getPassword()));
            user.setRole("HOD");
            user.setStatus(hod.getStatus() != null ? hod.getStatus() : "Active");
            appUserService.createUser(user);
        }
        return savedHOD;
    }

    public HOD updateHOD(Long id, HOD hodDetails) {
        return hodRepository.findById(id).map(hod -> {
            hod.setName(hodDetails.getName());
            hod.setEmail(hodDetails.getEmail());
            hod.setDepartment(hodDetails.getDepartment());
            hod.setPhone(hodDetails.getPhone());
            hod.setStatus(hodDetails.getStatus());
            return hodRepository.save(hod);
        }).orElse(null);
    }

    public void deleteHOD(Long id) {
        hodRepository.deleteById(id);
    }
} 