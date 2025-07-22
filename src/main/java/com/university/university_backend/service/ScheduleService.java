package com.university.university_backend.service;

import com.university.university_backend.entity.Schedule;
import com.university.university_backend.repository.ScheduleRepository;
import com.university.university_backend.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ActivityLogService activityLogService;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public Schedule createSchedule(Schedule schedule, String user) {
        Schedule saved = scheduleRepository.save(schedule);
        activityLogService.logActivity(user, "created schedule", "Schedule ID: " + saved.getId());
        return saved;
    }

    public Schedule updateSchedule(Long id, Schedule details, String user) {
        return scheduleRepository.findById(id).map(sch -> {
            sch.setFacultyId(details.getFacultyId());
            sch.setClassName(details.getClassName());
            sch.setCourseName(details.getCourseName());
            sch.setTime(details.getTime());
            Schedule updated = scheduleRepository.save(sch);
            activityLogService.logActivity(user, "updated schedule", "Schedule ID: " + updated.getId());
            return updated;
        }).orElse(null);
    }

    public void deleteSchedule(Long id, String user) {
        scheduleRepository.deleteById(id);
        activityLogService.logActivity(user, "deleted schedule", "Schedule ID: " + id);
    }
} 