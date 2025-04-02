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
@Table(name = "course", schema = "fsweb")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String name;

    @Column(name = "code")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 20)
    private String code;

    @Column(name = "credit")
    private Double credit;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "instructor_course", schema = "fsweb", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns =  @JoinColumn(name = "instructor_id"))
    private List<Instructor> instructors;

    public void addInstructor(Instructor instructor) {
        if (instructors == null)
            instructors = new ArrayList<>();
        instructors.add(instructor);
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj == this)
            return true;

        if ( obj == null || obj.getClass() != getClass())
            return false;

        Course course = (Course) obj;
        return course.getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}
