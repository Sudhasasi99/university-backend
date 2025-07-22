package com.university.university_backend.repository;

import com.university.university_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Additional query methods if needed
} 