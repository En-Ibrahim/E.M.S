package com.ems.TasksManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "project_task_report")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectTaskReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reportDate;

    private Long projectId;
    private String projectName;
    private String departmentName;

    private int totalTasks;

    private int todoCount;
    private int inProgressCount;
    private int nonSelectedCount;
    private int finishedCount;
    private int inReviewCount;

    private double completionPercentage;

    private boolean hasNoTasks;
}
