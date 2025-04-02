package com.workintech.university.controller;

import com.workintech.university.dto.CoursePatchRequestDto;
import com.workintech.university.dto.CourseRequestDto;
import com.workintech.university.dto.CourseResponseDto;
import com.workintech.university.dto.InstructorResponseDto;
import com.workintech.university.entity.Course;
import com.workintech.university.entity.Instructor;
import com.workintech.university.entity.University;
import com.workintech.university.service.CourseService;
import com.workintech.university.service.InstructorService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final InstructorService instructorService;

    @Autowired
    public CourseController(CourseService courseService, InstructorService instructorService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
    }
    @GetMapping("/")
    public List<CourseResponseDto> getCourses() {
        return courseService
                .getAll()
                .stream()
                .map(course -> new CourseResponseDto(
                        course.getName(),
                        course.getCode(),
                        course.getCredit(),
                        course.getDepartment().getName()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public CourseResponseDto getById(@Positive @PathVariable Long id){
        Course course = courseService.findById(id);
        return new CourseResponseDto(
                course.getName(),
                course.getCode(),
                course.getCredit(),
                course.getDepartment().getName());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED) //201
    public CourseResponseDto saveCourse(@Validated @RequestBody CourseRequestDto courseRequestDto) {

        Course course = new Course();
        course.setName(courseRequestDto.getName());
        course.setCode(courseRequestDto.getCode());
        course.setCredit(courseRequestDto.getCredit());

        Course savedCourse = courseService.create(course);
        return new CourseResponseDto(
                course.getName(),
                course.getCode(),
                course.getCredit(),
                course.getDepartment() == null ? null : course.getDepartment().getName()
        );
    }

    @PutMapping("/{id}")
    public CourseResponseDto replaceOrCreateCourse(@Positive @PathVariable Long id, @RequestBody CourseRequestDto courseRequestDto) {

        Course course = new Course();
        course.setName(courseRequestDto.getName());
        course.setCode(courseRequestDto.getCode());
        course.setCredit(courseRequestDto.getCredit());

        Course courseUpdated = courseService.replaceOrCreate(id, course);

        return new CourseResponseDto(
                courseUpdated.getName(),
                courseUpdated.getCode(),
                courseUpdated.getCredit(),
                courseUpdated.getDepartment().getName());
    }

    @PatchMapping("/{id}")
    public CourseResponseDto updateCourse(@Positive @PathVariable Long id, @Validated @RequestBody CoursePatchRequestDto coursePatchRequestDto) {

        Course course = new Course();

        if(coursePatchRequestDto.getName() != null)
            course.setName(coursePatchRequestDto.getName());
        if(coursePatchRequestDto.getCode() != null)
            course.setCode(coursePatchRequestDto.getCode());
        if(coursePatchRequestDto.getCredit() != null)
            course.setCredit(coursePatchRequestDto.getCredit());

        Course courseUpdated = courseService.update(id, course);

        return new CourseResponseDto(
                courseUpdated.getName(),
                courseUpdated.getCode(),
                courseUpdated.getCredit(),
                courseUpdated.getDepartment().getName()
        );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void deleteCourse(@Positive @PathVariable Long id){
        courseService.deleteById(id);
    }

    @GetMapping("/{id}/instructors") //Get all instructors of a course with given id.
    public List<InstructorResponseDto> getAllInstructorsByCourseId(@Positive @PathVariable Long id) {
        Course course = courseService.findById(id);
       return course
                .getInstructors()
                .stream()
                .map(instructor -> new InstructorResponseDto(
                        instructor.getFirstName(),
                        instructor.getLastName(),
                        instructor.getEmail(),
                        instructor.getPhoneNumber()))
                .toList();
    }

    @PutMapping("/{id}/instructors/{instructorId}")
    public CourseResponseDto assignInstructorToCourse(@Positive @PathVariable("id") Long courseId, @Positive @PathVariable("instructorId") Long instructorId) {

        Course course = courseService.findById(courseId);
        Instructor instructor = instructorService.findById(instructorId);

        course.addInstructor(instructor);
        instructor.addCourse(course);

        courseService.replaceOrCreate(courseId, course);

        return new CourseResponseDto(
                course.getName(),
                course.getCode(),
                course.getCredit(),
                course.getDepartment() == null ? null : course.getDepartment().getName());
    }



}
