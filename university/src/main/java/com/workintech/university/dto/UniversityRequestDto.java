package com.workintech.university.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityRequestDto {

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String name;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 500)
    private String address;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String email;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 20)
    private String phoneNumber;
}
