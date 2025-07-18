package com.ems.TasksManagementSystem.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportProjectTaskDto {
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
