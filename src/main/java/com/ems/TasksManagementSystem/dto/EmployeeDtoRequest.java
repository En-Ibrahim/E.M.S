package com.ems.TasksManagementSystem.dto;


import com.ems.TasksManagementSystem.entity.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private String address;
    private Date date;
    private String nationality;

    private List<String> skills = new ArrayList<>();

    private int Experience_Years;

    private String educationLevels;

    private String phoneNumber;

    private String position;


    private String department;

    //salary & role & hire date

    private double salary;

    private Role role;

    private LocalDateTime dateTime;


}
