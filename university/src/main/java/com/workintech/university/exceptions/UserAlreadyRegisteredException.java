package com.workintech.university.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserAlreadyRegisteredException extends UniversityException{
    public UserAlreadyRegisteredException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
