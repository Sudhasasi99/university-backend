package com.university.university_backend.service;

import com.university.university_backend.entity.Course;
import com.university.university_backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course details) {
        return courseRepository.findById(id).map(c -> {
            c.setName(details.getName());
            c.setCode(details.getCode());
            c.setDescription(details.getDescription());
            c.setDepartmentId(details.getDepartmentId());
            return courseRepository.save(c);
        }).orElse(null);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
} 