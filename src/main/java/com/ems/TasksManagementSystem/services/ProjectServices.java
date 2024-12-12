package com.ems.TasksManagementSystem.services;


import com.ems.TasksManagementSystem.dto.ProjectDto;
import com.ems.TasksManagementSystem.entity.Project;
import com.ems.TasksManagementSystem.mapper.ProjectMapper;
import com.ems.TasksManagementSystem.repo.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServices {


    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private final ProjectMapper mapper;

    public ProjectDto addProject(Project project) {
        return mapper.mapToDTO(projectRepo.save(project));
    }

    public ProjectDto updateProject(Project entity) {
        Optional<Project> project = projectRepo.findById(entity.getProject_id());
        if (!project.isEmpty() && project.isPresent())
            return mapper.mapToDTO(projectRepo.save(project.get()));
        else
            throw new IllegalStateException("Not found employee");
    }

    public ProjectDto findById(Long id) {
        return mapper.mapToDTO(projectRepo.findById(id).orElseThrow());
    }


    public List<ProjectDto> findAll() {
        return mapper.mapToDTO(projectRepo.findAll());
    }

    public void delete(Long id) {
        Optional<Project> project = projectRepo.findById(id);
        if (!project.isEmpty() && project.isPresent())
            projectRepo.delete(project.get());
        else
            throw new IllegalStateException("Not found employee");
    }

}
