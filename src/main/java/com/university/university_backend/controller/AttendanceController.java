package com.university.university_backend.controller;

import com.university.university_backend.entity.Attendance;
import com.university.university_backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
        Optional<Attendance> att = attendanceService.getAttendanceById(id);
        return att.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Attendance markAttendance(@RequestBody Attendance attendance, @RequestParam String user) {
        return attendanceService.markAttendance(attendance, user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody Attendance details, @RequestParam String user) {
        Attendance updated = attendanceService.updateAttendance(id, details, user);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id, @RequestParam String user) {
        attendanceService.deleteAttendance(id, user);
        return ResponseEntity.noContent().build();
    }
} 