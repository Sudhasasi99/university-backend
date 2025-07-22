package com.university.university_backend.repository;

import com.university.university_backend.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    // Additional query methods if needed
} 