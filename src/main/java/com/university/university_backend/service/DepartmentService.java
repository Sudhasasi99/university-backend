package com.university.university_backend.service;

import com.university.university_backend.entity.Department;
import com.university.university_backend.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department details) {
        return departmentRepository.findById(id).map(dept -> {
            dept.setName(details.getName());
            dept.setCode(details.getCode());
            dept.setDescription(details.getDescription());
            return departmentRepository.save(dept);
        }).orElse(null);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
} 