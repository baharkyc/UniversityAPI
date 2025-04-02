package com.workintech.university.service;

import com.workintech.university.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();
    Department findById(Long id);
    Department create(Department department);
    Department replaceOrCreate(Long id, Department department);
    Department update(Long id, Department department);
    void deleteById(Long id);
}
