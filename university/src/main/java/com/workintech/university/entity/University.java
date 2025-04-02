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
@Table(name = "university", schema = "fsweb")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String name;

    @Column(name = "address")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 500)
    private String address;

    @Column(name = "email")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String email;

    @Column(name = "phone_number")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 20)
    private String phoneNumber;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Faculty> faculties;

    public void addFaculty(Faculty faculty) {
        if (faculties == null)
            faculties = new ArrayList<>();
        faculties.add(faculty);
    }

    public boolean equals(Object obj) {

        if(obj == this)
            return true;

        if(obj == null || obj.getClass() != getClass())
            return false;

        University university = (University) obj;

        return university.getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}
