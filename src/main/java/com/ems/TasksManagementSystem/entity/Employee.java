package com.ems.TasksManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emp_id;
    @NotNull
    private String firstName;
    private String lastName;

    @Email
    private String email;
    @NotNull
    private String password;

    private String address;

    private String nationality;

    private List<String> skills = new ArrayList<>();

    private int Experience_Years;

    private String educationLevels;


    private String phoneNumber;

    @NotNull
    private String position;


    @ManyToOne
    private Department department;

    //salary & role & hire date

    private double salary;

    private Role role;

    private LocalDateTime date_Hiring;

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
