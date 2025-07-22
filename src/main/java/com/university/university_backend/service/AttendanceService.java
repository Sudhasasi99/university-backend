package com.university.university_backend.service;

import com.university.university_backend.entity.Attendance;
import com.university.university_backend.repository.AttendanceRepository;
import com.university.university_backend.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private ActivityLogService activityLogService;

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }

    public Attendance markAttendance(Attendance attendance, String user) {
        Attendance saved = attendanceRepository.save(attendance);
        activityLogService.logActivity(user, "marked attendance", "Attendance ID: " + saved.getId());
        return saved;
    }

    public Attendance updateAttendance(Long id, Attendance details, String user) {
        return attendanceRepository.findById(id).map(att -> {
            att.setStatus(details.getStatus());
            Attendance updated = attendanceRepository.save(att);
            activityLogService.logActivity(user, "updated attendance", "Attendance ID: " + updated.getId());
            return updated;
        }).orElse(null);
    }

    public void deleteAttendance(Long id, String user) {
        attendanceRepository.deleteById(id);
        activityLogService.logActivity(user, "deleted attendance", "Attendance ID: " + id);
    }
} 