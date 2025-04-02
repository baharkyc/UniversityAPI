package com.workintech.university.service;

import com.workintech.university.entity.Faculty;
import com.workintech.university.exceptions.UniversityException;
import com.workintech.university.repository.FacultyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FacultyServiceImpl implements FacultyService{

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty findById(Long id) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);
        if(facultyOptional.isPresent())
            return facultyOptional.get();
        else {
            throw new UniversityException("Faculty with id: " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty replaceOrCreate(Long id, Faculty faculty) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);
        if(facultyOptional.isPresent())
            faculty.setId(id);
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        Faculty facultyToUpdate = facultyRepository.findById(id).orElseThrow(() -> new UniversityException("Faculty with id: " + id + " not found", HttpStatus.NOT_FOUND));
        if(faculty.getName() != null)
            facultyToUpdate.setName(faculty.getName());
        return facultyRepository.save(facultyToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        facultyRepository.deleteById(id);
    }
}
