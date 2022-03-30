package com.example.university.service;

import com.example.university.model.Department;
import com.example.university.repository.DepartmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DepartmentService {

    DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() { return departmentRepository.findAll(); }

    @Transactional(readOnly = true)
    public Department getById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity with id=" + id + " not found"));
    }

    @Transactional
    public Department createDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        return departmentRepository.save(department);
    }

    @Transactional
    public Department updateDepartment(Long id, String name) {
        Department department = getById(id);
        department.setName(name);
        return departmentRepository.save(department);
    }

    @Transactional
    public Long deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
        return id;
    }
}
