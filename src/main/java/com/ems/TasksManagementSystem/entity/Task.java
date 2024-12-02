package com.ems.TasksManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long task_id;

    private Project project;
    private String name;
    private String description;
    private State state;


}
