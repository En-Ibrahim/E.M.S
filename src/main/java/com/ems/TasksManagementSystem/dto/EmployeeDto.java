package com.ems.TasksManagementSystem.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EmployeeDto {


    private String name;
    private String email;
    private String position;

    private String department;


}
