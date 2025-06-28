package com.ems.TasksManagementSystem.dto;


import com.ems.TasksManagementSystem.entity.Employee;
import lombok.*;

@Data
//@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {


    private String name;

    private String manager;
}
