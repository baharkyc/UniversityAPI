package com.workintech.university.service;

import com.workintech.university.entity.Department;
import com.workintech.university.exceptions.UniversityException;
import com.workintech.university.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new UniversityException("Department with id: " + id + " not found.", HttpStatus.NOT_FOUND));
        return department;
    }

    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department replaceOrCreate(Long id, Department department) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(departmentOptional.isPresent())
            department.setId(id);
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Long id, Department department) {
        Department departmentToUpdate = departmentRepository.findById(id).orElseThrow(() -> new UniversityException("Department with id: " + id + " not found.", HttpStatus.NOT_FOUND));
        if(department.getName() != null)
            departmentToUpdate.setName(department.getName());
        return departmentRepository.save(departmentToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}
