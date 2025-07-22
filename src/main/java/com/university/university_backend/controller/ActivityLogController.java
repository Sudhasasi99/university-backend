package com.university.university_backend.controller;

import com.university.university_backend.entity.ActivityLog;
import com.university.university_backend.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activity")
public class ActivityLogController {
    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping("/recent")
    public List<ActivityLog> getRecentActivities(@RequestParam(defaultValue = "10") int limit) {
        return activityLogService.getRecentActivities(limit);
    }
} 