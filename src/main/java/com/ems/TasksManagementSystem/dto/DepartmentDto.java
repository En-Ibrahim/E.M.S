package com.ems.TasksManagementSystem.dto;


import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long dept_id;

    private String name;

    private Long manager;
}
