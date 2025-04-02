package com.workintech.university.service;

import com.workintech.university.entity.Faculty;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAll();
    Faculty findById(Long id);
    Faculty create(Faculty faculty);
    Faculty replaceOrCreate(Long id, Faculty faculty);
    Faculty update(Long id, Faculty faculty);
    void deleteById(Long id);
}
