package com.workintech.university.service;

import com.workintech.university.entity.University;
import com.workintech.university.exceptions.UniversityException;
import com.workintech.university.repository.UniversityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UniversityServiceImpl implements UniversityService{

    @Autowired
    private final UniversityRepository universityRepository;

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
    }

    @Override
    public University findById(Long id) {
        Optional<University> university = universityRepository.findById(id);
        if(university.isPresent())
            return university.get();
        else throw new UniversityException("University with id:" + id + " not found.", HttpStatus.NOT_FOUND);
    }

    @Override
    public University create(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University replaceOrCreate(Long id, University university) {
        Optional<University> uniOptional = universityRepository.findById(id);
        if(uniOptional.isPresent()){
            university.setId(id);
            universityRepository.save(university);
        }
        return universityRepository.save(university);
    }

    @Override
    public University update(Long id, University university) {
        University universityToUpdate = universityRepository.findById(id)
                .orElseThrow(() -> new UniversityException(id + " id'li University bulunamadı.", HttpStatus.NOT_FOUND));
        if(university.getName() != null) {
            universityToUpdate.setName(university.getName());
        }
        return universityRepository.save(universityToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        universityRepository.deleteById(id);
    }
}
