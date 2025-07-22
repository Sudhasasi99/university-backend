package com.university.university_backend.repository;

import com.university.university_backend.entity.HOD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HODRepository extends JpaRepository<HOD, Long> {
    // Additional query methods if needed
} 