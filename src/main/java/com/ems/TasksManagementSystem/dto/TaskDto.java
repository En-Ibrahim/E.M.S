package com.ems.TasksManagementSystem.dto;


import com.ems.TasksManagementSystem.entity.State;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskDto {
    private Long task_id;

    private String name;
    private String description;
    private State state;


    private String project;

    private String assign_to;

}
