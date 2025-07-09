package com.ems.TasksManagementSystem.auth.config;

import com.ems.TasksManagementSystem.entity.Employee;
import com.ems.TasksManagementSystem.entity.Role;
import com.ems.TasksManagementSystem.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminInitializer {

    private static final String ADMIN_EMAIL = "ibrahimashraf643@gmail.com";
    private static final String ADMIN_PASSWORD = "123456";

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void ensureAdminExists() {
        Optional<Employee> adminOpt = employeeRepo.findByEmail(ADMIN_EMAIL);
        if (adminOpt.isEmpty()) {
            Employee admin = Employee.builder()
                    .firstName("Ibrahim")
                    .lastName("Ashraf")
                    .email(ADMIN_EMAIL)
                    .password(passwordEncoder.encode(ADMIN_PASSWORD))
                    .position("OWNER")
                    .roles(Role.ADMIN)
                    .build();
            employeeRepo.save(admin);
            System.out.println("Default admin user created: " + ADMIN_EMAIL);
        } else {
            System.out.println("Admin user already exists: " + ADMIN_EMAIL);
        }
    }
} 