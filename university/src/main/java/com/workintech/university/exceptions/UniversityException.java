package com.workintech.university.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UniversityException extends RuntimeException{
    private HttpStatus httpStatus;

    public UniversityException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}