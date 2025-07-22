package com.university.university_backend.repository;

import com.university.university_backend.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    // Additional query methods if needed
} 