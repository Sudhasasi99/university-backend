package com.university.university_backend.repository;

import com.university.university_backend.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // Additional query methods if needed
} 