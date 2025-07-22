package com.university.university_backend.repository;

import com.university.university_backend.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    // Additional query methods if needed
} 