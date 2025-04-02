package com.workintech.university.service;

import com.workintech.university.entity.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAll();
    Instructor findById(Long id);
    Instructor create(Instructor instructor);
    Instructor replaceOrCreate(Long id, Instructor instructor);
    Instructor update(Long id, Instructor instructor);
    void deleteById(Long id);
}
