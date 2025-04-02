package com.workintech.university.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "instructor", schema = "fsweb")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String lastName;

    @Column(name = "phone_number")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 20)
    private String phoneNumber;

    @Column(name = "email")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 20)
    private String email;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "instructor_course", schema = "fsweb", joinColumns = @JoinColumn(name = "instructor_id"), inverseJoinColumns =  @JoinColumn(name = "course_id"))
    private List<Course> courses;

    public void addCourse(Course course) {
        if (courses == null)
            courses = new ArrayList<>();
        courses.add(course);
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj == this)
            return true;

        if ( obj == null || obj.getClass() != getClass())
            return false;

        Instructor instructor = (Instructor) obj;

        return instructor.getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}
