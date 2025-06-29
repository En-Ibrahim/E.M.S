package com.ems.TasksManagementSystem.dto;


import com.ems.TasksManagementSystem.entity.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EmployeeDtoRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private int Experience_Years;

    private String position;

    private String department;

    private double salary;

    private Role role;


}
