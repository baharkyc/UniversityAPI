package com.workintech.university.service;

import com.workintech.university.entity.University;

import java.util.List;

public interface UniversityService {
    List<University> getAll();
    University findById(Long id);
    University create(University university);
    University replaceOrCreate(Long id, University university);
    University update(Long id, University university);
    void deleteById(Long id);
}
