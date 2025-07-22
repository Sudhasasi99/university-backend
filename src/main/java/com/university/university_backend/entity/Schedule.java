package com.university.university_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long facultyId;
    private String className;
    private String courseName;
    private String time; // e.g., "Mon 10:00-11:00" or use LocalDateTime for more detail

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFacultyId() { return facultyId; }
    public void setFacultyId(Long facultyId) { this.facultyId = facultyId; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
} 