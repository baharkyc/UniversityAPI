package com.workintech.university.controller;

import com.workintech.university.dto.FacultyResponseDto;
import com.workintech.university.dto.UniversityPatchRequestDto;
import com.workintech.university.dto.UniversityRequestDto;
import com.workintech.university.dto.UniversityResponseDto;
import com.workintech.university.entity.University;
import com.workintech.university.service.UniversityService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/")
    public List<UniversityResponseDto> getUniversities() {

        return universityService.getAll()
                .stream()
                .map(university -> new UniversityResponseDto(
                        university.getName(),
                        university.getAddress(),
                        university.getEmail(),
                        university.getPhoneNumber()))
                .toList();
    }

    @GetMapping("/{id}")
    public UniversityResponseDto getById(@Positive @PathVariable Long id){
        University university = universityService.findById(id);
        return new UniversityResponseDto(
                university.getName(),
                university.getAddress(),
                university.getEmail(),
                university.getPhoneNumber()
        );
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED) //201
    public UniversityResponseDto createUniversity(@Validated @RequestBody UniversityRequestDto universityRequestDto) {

        University university = new University();
        university.setName(universityRequestDto.getName());
        university.setAddress(universityRequestDto.getAddress());
        university.setEmail(universityRequestDto.getEmail());
        university.setPhoneNumber(university.getPhoneNumber());

        universityService.create(university);

        return new UniversityResponseDto(
                university.getName(),
                university.getAddress(),
                university.getEmail(),
                university.getPhoneNumber()
        );
    }

    @PutMapping("/{id}")
    public UniversityResponseDto replaceOrCreateUniversity(@Positive @PathVariable Long id, @Validated @RequestBody UniversityRequestDto universityRequestDto) {

        University university = new University();
        university.setName(universityRequestDto.getName());
        university.setAddress(universityRequestDto.getAddress());
        university.setEmail(universityRequestDto.getEmail());
        university.setPhoneNumber(university.getPhoneNumber());

        universityService.replaceOrCreate(id, university);

        return new UniversityResponseDto(
                university.getName(),
                university.getAddress(),
                university.getEmail(),
                university.getPhoneNumber()
        );
    }

    @PatchMapping("/{id}")
    public UniversityResponseDto updateUniversity(@Positive @PathVariable Long id, @Validated @RequestBody UniversityPatchRequestDto universityPatchRequestDto) {

        University university = new University();
        if(universityPatchRequestDto.getName() != null)
            university.setName(universityPatchRequestDto.getName());
        if(universityPatchRequestDto.getAddress() != null)
            university.setAddress(universityPatchRequestDto.getAddress());
        if(universityPatchRequestDto.getEmail() != null)
            university.setEmail(universityPatchRequestDto.getEmail());
        if(universityPatchRequestDto.getPhoneNumber() != null)
            university.setPhoneNumber(university.getPhoneNumber());

        universityService.update(id, university);

        return new UniversityResponseDto(
                university.getName(),
                university.getAddress(),
                university.getEmail(),
                university.getPhoneNumber()
        );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUniversity(@Positive @PathVariable Long id){

        universityService.deleteById(id);
    }

    @GetMapping("/{id}/faculties")
    public List<FacultyResponseDto> getFacultiesOfUniversity(@Positive @PathVariable Long id) {

        University university = universityService.findById(id);

        return university
                .getFaculties()
                .stream()
                .map(faculty -> new FacultyResponseDto(
                        faculty.getName(),
                        faculty.getPhoneNumber(),
                        faculty.getEmail(),
                        faculty.getAddress()
                )).toList();
    }
}

