package com.ems.TasksManagementSystem.auth.controller;

import com.ems.TasksManagementSystem.auth.model.AuthenticationRequest;
import com.ems.TasksManagementSystem.auth.model.AuthenticationResponse;
import com.ems.TasksManagementSystem.auth.serrvice.AuthenticationServices;
import com.ems.TasksManagementSystem.entity.Employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationServices authService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Employee user) {

        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
