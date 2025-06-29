package com.ems.TasksManagementSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
//@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {


    private String name;

    private String manager;
}
