package com.workintech.university.controller;

import com.workintech.university.dto.RegisterRequestDto;
import com.workintech.university.dto.RegisterResponseDto;
import com.workintech.university.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//handles register
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponseDto register(@Validated @RequestBody RegisterRequestDto request) {
        authService.register(request.getEmail(), request.getPassword());
        return new RegisterResponseDto("User account created succesfully.");
    }
}
