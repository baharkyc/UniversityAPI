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
@Table(name = "faculty", schema = "fsweb")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String name;

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

    @Column(name = "address")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 200)
    private String address;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "university_id")
    private University university;

    @OneToMany(mappedBy = "faculty")
    private List<Department> departments;

    public void addDepartment(Department department) {
        if (departments == null)
            departments = new ArrayList<>();
        departments.add(department);
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj == this)
            return true;

        if ( obj == null || obj.getClass() != getClass())
            return false;

        Faculty faculty = (Faculty) obj;

        return faculty.getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}
