package com.ems.TasksManagementSystem.controller;


import com.ems.TasksManagementSystem.dto.ReportProjectTaskDto;
import com.ems.TasksManagementSystem.entity.ProjectTaskReport;
import com.ems.TasksManagementSystem.mapper.ReportProjectTaskMapper;
import com.ems.TasksManagementSystem.services.ReportGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportGenerationService reportGenerationService;
    private final ReportProjectTaskMapper reportProjectTaskMapper;

    @GetMapping("/generate/project-task")
    public ResponseEntity<?> generateProjectTaskReport(
            @RequestParam(name = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate reportDate
    ) {
        if (reportDate == null) {
            reportDate = LocalDate.now();
        }

        ProjectTaskReport report = reportGenerationService.generateProjectTaskReport(reportDate);
        ReportProjectTaskDto dto = reportProjectTaskMapper.entityToDto(report);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/generate/all")
    public ResponseEntity<?> generateAllReports() {
        List<ProjectTaskReport> reports=reportGenerationService.generateAllProjectTaskReports(LocalDate.now());
        // Add other reports here...
        return ResponseEntity.ok(reports);
    }
}