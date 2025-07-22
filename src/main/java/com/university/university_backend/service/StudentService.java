package com.university.university_backend.service;

import com.university.university_backend.entity.Student;
import com.university.university_backend.repository.StudentRepository;
import com.university.university_backend.entity.AppUser;
import com.university.university_backend.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AppUserService appUserService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        // Also create AppUser for login
        if (student.getEmail() != null && student.getPassword() != null) {
            AppUser user = new AppUser();
            user.setName(student.getFullName());
            user.setEmail(student.getEmail());
            user.setPassword(passwordEncoder.encode(student.getPassword()));
            user.setRole("STUDENT");
            user.setStatus(student.getStatus() != null ? student.getStatus() : "Active");
            appUserService.createUser(user);
        }
        return savedStudent;
    }

    public Student updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setRollNumber(studentDetails.getRollNumber());
            student.setFullName(studentDetails.getFullName());
            student.setEmail(studentDetails.getEmail());
            student.setDepartment(studentDetails.getDepartment());
            student.setClassName(studentDetails.getClassName());
            student.setSemester(studentDetails.getSemester());
            student.setPhone(studentDetails.getPhone());
            student.setStatus(studentDetails.getStatus());
            student.setFacultyId(studentDetails.getFacultyId()); // <-- add this line
            return studentRepository.save(student);
        }).orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
} 