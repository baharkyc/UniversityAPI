package com.workintech.university.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityErrorResponse {
    private String message;
    private int status;
    private long timestamp;
}
