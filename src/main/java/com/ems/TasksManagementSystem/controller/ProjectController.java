package com.ems.TasksManagementSystem.controller;


import com.ems.TasksManagementSystem.dto.ProjectDto;
import com.ems.TasksManagementSystem.entity.Project;
import com.ems.TasksManagementSystem.services.ProjectServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    @Autowired
    private ProjectServices services;

    @PostMapping
    public ResponseEntity<?> addProject(@RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(services.addProject(projectDto));
    }

    @PutMapping
    public ResponseEntity<?> updateProject(@RequestBody Project project) {
        return ResponseEntity.ok(services.updateProject(project));
    }

    @GetMapping("/id")
    public ResponseEntity<?> findByID(Long id) {
        return ResponseEntity.ok(services.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(services.findAll());
    }


    @DeleteMapping
    public ResponseEntity<?> delete(Long id) {
        services.delete(id);
        return ResponseEntity.ok(null);
    }


}
