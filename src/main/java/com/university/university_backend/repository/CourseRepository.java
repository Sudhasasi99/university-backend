package com.university.university_backend.repository;

import com.university.university_backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Additional query methods if needed
} 