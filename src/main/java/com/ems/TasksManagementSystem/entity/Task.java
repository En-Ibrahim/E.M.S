package com.ems.TasksManagementSystem.entity;

import jakarta.persistence.*;
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

    private String name;
    private String description;
    private State state;

    @ManyToOne
    private Project project;
    @ManyToOne
    private Employee belongTo;

}
