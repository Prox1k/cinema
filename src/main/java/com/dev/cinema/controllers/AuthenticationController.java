package com.dev.cinema.controllers;

import com.dev.cinema.dto.UserDto;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) {
        try {
            authenticationService.login(userDto.getEmail(), userDto.getPassword());
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        return "Login successful!";
    }

    @PostMapping("/registration")
    public String register(@RequestBody UserDto userDto) {
        try {
            authenticationService.register(userDto.getEmail(), userDto.getPassword());
        } catch (DataProcessingException e) {
            return e.getMessage();
        }
        return "Registration successful!";
    }
}
