package com.workintech.university.service;

import com.workintech.university.entity.Instructor;
import com.workintech.university.exceptions.UniversityException;
import com.workintech.university.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InstructorServiceImpl implements InstructorService{
    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public List<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(Long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new UniversityException("Instructor with id: " + id + " not found.", HttpStatus.NOT_FOUND));
        return instructor;
    }

    @Override
    public Instructor create(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor replaceOrCreate(Long id, Instructor instructor) {
        Optional<Instructor> instructorOptional = instructorRepository.findById(id);
        if(instructorOptional.isPresent()) {
            instructor.setId(id);
        }
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor update(Long id, Instructor instructor) {
        Instructor instructorToUpdate = instructorRepository.findById(id).orElseThrow(() -> new UniversityException("Instructor with id: " + id + " not found.", HttpStatus.NOT_FOUND));
        if(instructor.getFirstName() != null) {
            instructorToUpdate.setFirstName(instructor.getFirstName());
        }
        return  instructorRepository.save(instructorToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }
}
