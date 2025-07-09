package com.ems.TasksManagementSystem.auth.serrvice;

import com.ems.TasksManagementSystem.auth.jwt.JwtService;
import com.ems.TasksManagementSystem.auth.model.AuthenticationRequest;
import com.ems.TasksManagementSystem.auth.model.AuthenticationResponse;
import com.ems.TasksManagementSystem.entity.Employee;
import com.ems.TasksManagementSystem.repo.EmployeeRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServices {

    private final EmployeeRepo employeeRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(Employee user) {
        Optional<Employee> user1 = employeeRepo.findByEmail(user.getEmail());
        if (user1.isPresent() && !user1.isEmpty() && user1 != null) {
            throw new IllegalStateException("this user is already created");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        employeeRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = employeeRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }


}
