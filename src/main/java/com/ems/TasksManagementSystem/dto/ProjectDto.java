package com.ems.TasksManagementSystem.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDto {

    private Long project_id;

    private String name;
    private String description;

}
