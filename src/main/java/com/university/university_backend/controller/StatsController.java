package com.university.university_backend.controller;

import com.university.university_backend.service.StudentService;
import com.university.university_backend.service.FacultyService;
import com.university.university_backend.service.HODService;
import com.university.university_backend.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
    @Autowired private StudentService studentService;
    @Autowired private FacultyService facultyService;
    @Autowired private HODService hodService;
    @Autowired private AppUserService appUserService;

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("students", studentService.getAllStudents().size());
        stats.put("faculty", facultyService.getAllFaculty().size());
        stats.put("hods", hodService.getAllHODs().size());
        stats.put("users", appUserService.getAllUsers().size());
        return stats;
    }
} 