package com.workintech.university.service;

import com.workintech.university.entity.Course;
import com.workintech.university.entity.Department;
import com.workintech.university.exceptions.UniversityException;
import com.workintech.university.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new UniversityException("Course with id: " + id + " not found", HttpStatus.NOT_FOUND));
        return course;
    }

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course replaceOrCreate(Long id, Course course) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isPresent())
            course.setId(id);
        return courseRepository.save(course);
    }

    @Override
    public Course update(Long id, Course course) {
        Course courseToUpdate = courseRepository.findById(id).orElseThrow(() -> new UniversityException("Course with id: " + id + " not found.", HttpStatus.NOT_FOUND));
        if(course.getName() != null)
            courseToUpdate.setName(course.getName());
        return courseRepository.save(courseToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}
