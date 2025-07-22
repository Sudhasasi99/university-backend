package com.university.university_backend.service;

import com.university.university_backend.entity.Faculty;
import com.university.university_backend.repository.FacultyRepository;
import com.university.university_backend.entity.AppUser;
import com.university.university_backend.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private AppUserService appUserService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Optional<Faculty> getFacultyById(Long id) {
        return facultyRepository.findById(id);
    }

    public Faculty createFaculty(Faculty faculty) {
        Faculty savedFaculty = facultyRepository.save(faculty);
        // Also create AppUser for login
        if (faculty.getEmail() != null && faculty.getPassword() != null) {
            AppUser user = new AppUser();
            user.setName(faculty.getName());
            user.setEmail(faculty.getEmail());
            user.setPassword(passwordEncoder.encode(faculty.getPassword()));
            user.setRole("FACULTY");
            user.setStatus(faculty.getStatus() != null ? faculty.getStatus() : "Active");
            appUserService.createUser(user);
        }
        return savedFaculty;
    }

    public Faculty updateFaculty(Long id, Faculty facultyDetails) {
        return facultyRepository.findById(id).map(faculty -> {
            faculty.setName(facultyDetails.getName());
            faculty.setEmail(facultyDetails.getEmail());
            faculty.setDepartment(facultyDetails.getDepartment());
            faculty.setPhone(facultyDetails.getPhone());
            faculty.setStatus(facultyDetails.getStatus());
            return facultyRepository.save(faculty);
        }).orElse(null);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
} 