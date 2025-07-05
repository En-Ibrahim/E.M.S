package com.ems.TasksManagementSystem.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EmployeeDto {

    private Long emp_id;
    private String name;
    private String email;
    private String position;

    private String department;


}
