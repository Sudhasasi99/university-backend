package com.university.university_backend.repository;

import com.university.university_backend.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Additional query methods if needed
} 