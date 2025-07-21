package com.ems.TasksManagementSystem.services;

import com.ems.TasksManagementSystem.entity.Project;
import com.ems.TasksManagementSystem.entity.ProjectTaskReport;
import com.ems.TasksManagementSystem.entity.State;
import com.ems.TasksManagementSystem.repo.ProjectRepo;
import com.ems.TasksManagementSystem.repo.ProjectTaskReportRepo;
import com.ems.TasksManagementSystem.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportGenerationService {

    private final ProjectRepo projectRepository;
    private final TaskRepo taskRepository;
    private final ProjectTaskReportRepo projectTaskReportRepository;


    // this one report back
    public ProjectTaskReport generateProjectTaskReport(LocalDate reportDate) {
        List<Project> allProjects = projectRepository.findAll();
        ProjectTaskReport report= new ProjectTaskReport();
        for (Project project : allProjects) {
            Long projectId = project.getProject_id();
            String projectName = project.getName();
            String departmentName = project.getDepartment() != null
                    ? project.getDepartment().getName()
                    : "Unknown";

            int totalTasks = taskRepository.countByProjectId(projectId);
            int todoCount = taskRepository.countByProjectIdAndState(projectId, State.TODO);
            int inProgressCount = taskRepository.countByProjectIdAndState(projectId, State.IN_PROGRESS);
            int nonSelectedCount = taskRepository.countByProjectIdAndState(projectId, State.NON_SELECTED);
            int finishedCount = taskRepository.countByProjectIdAndState(projectId, State.FINISHED);
            int inReviewCount = taskRepository.countByProjectIdAndState(projectId, State.IN_REVIEW);

            double completionPercentage = (totalTasks == 0)
                    ? 0.0
                    : ((double) finishedCount / totalTasks) * 100;

            boolean hasNoTasks = (totalTasks == 0);

             report = ProjectTaskReport.builder()
                    .reportDate(reportDate)
                    .projectId(projectId)
                    .projectName(projectName)
                    .departmentName(departmentName)
                    .totalTasks(totalTasks)
                    .todoCount(todoCount)
                    .inProgressCount(inProgressCount)
                    .nonSelectedCount(nonSelectedCount)
                    .finishedCount(finishedCount)
                    .inReviewCount(inReviewCount)
                    .completionPercentage(completionPercentage)
                    .hasNoTasks(hasNoTasks)
                    .build();

            projectTaskReportRepository.save(report);
        }
        return report;
    }
    //this is list of reports back
    public List<ProjectTaskReport> generateAllProjectTaskReports(LocalDate reportDate) {
        List<Project> allProjects = projectRepository.findAll();
        List<ProjectTaskReport> reportList = new ArrayList<>();

        for (Project project : allProjects) {
            Long projectId = project.getProject_id();
            String projectName = project.getName();
            String departmentName = project.getDepartment() != null
                    ? project.getDepartment().getName()
                    : "Unknown";

            int totalTasks = taskRepository.countByProjectId(projectId);
            int todoCount = taskRepository.countByProjectIdAndState(projectId, State.TODO);
            int inProgressCount = taskRepository.countByProjectIdAndState(projectId, State.IN_PROGRESS);
            int nonSelectedCount = taskRepository.countByProjectIdAndState(projectId, State.NON_SELECTED);
            int finishedCount = taskRepository.countByProjectIdAndState(projectId, State.FINISHED);
            int inReviewCount = taskRepository.countByProjectIdAndState(projectId, State.IN_REVIEW);

            double completionPercentage = (totalTasks == 0)
                    ? 0.0
                    : ((double) finishedCount / totalTasks) * 100;

            boolean hasNoTasks = (totalTasks == 0);

            ProjectTaskReport report = ProjectTaskReport.builder()
                    .reportDate(reportDate)
                    .projectId(projectId)
                    .projectName(projectName)
                    .departmentName(departmentName)
                    .totalTasks(totalTasks)
                    .todoCount(todoCount)
                    .inProgressCount(inProgressCount)
                    .nonSelectedCount(nonSelectedCount)
                    .finishedCount(finishedCount)
                    .inReviewCount(inReviewCount)
                    .completionPercentage(completionPercentage)
                    .hasNoTasks(hasNoTasks)
                    .build();

            projectTaskReportRepository.save(report);
            reportList.add(report);
        }

        return reportList;
    }


}
