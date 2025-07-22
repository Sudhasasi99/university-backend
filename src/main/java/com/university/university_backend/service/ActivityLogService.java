package com.university.university_backend.service;

import com.university.university_backend.entity.ActivityLog;
import com.university.university_backend.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityLogService {
    @Autowired
    private ActivityLogRepository activityLogRepository;

    public void logActivity(String user, String action, String details) {
        ActivityLog log = new ActivityLog();
        log.setUser(user);
        log.setAction(action);
        log.setDetails(details);
        log.setTimestamp(LocalDateTime.now());
        activityLogRepository.save(log);
    }

    public List<ActivityLog> getRecentActivities(int limit) {
        return activityLogRepository.findAll()
                .stream()
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                .limit(limit)
                .toList();
    }
} 