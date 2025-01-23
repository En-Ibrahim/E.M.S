package com.ems.TasksManagementSystem.services;


import com.ems.TasksManagementSystem.dto.ProjectDto;
import com.ems.TasksManagementSystem.entity.Project;
import com.ems.TasksManagementSystem.exception.BadRequestException;
import com.ems.TasksManagementSystem.exception.RecordNotFoundException;
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
        if(project!=null && project.getName()!=null)
            return mapper.mapToDTO(projectRepo.save(project));
        else
            throw new BadRequestException("Entry correct data");
    }

    public ProjectDto updateProject(Project entity) {
        Optional<Project> project = projectRepo.findById(entity.getProject_id());
        if (!project.isEmpty() && project.isPresent())
            return mapper.mapToDTO(projectRepo.save(project.get()));
        else
            throw new RecordNotFoundException("Not found employee");
    }

    public ProjectDto findById(Long id) {
        Optional<Project> optional = projectRepo.findById(id);
        if(!optional.isEmpty() && optional.isPresent())
            return mapper.mapToDTO(optional.get());
        else
            throw new RecordNotFoundException("Not found project");
    }


    public List<ProjectDto> findAll() {
        if(projectRepo.findAll()!=null)
           return mapper.mapToDTO(projectRepo.findAll());
        else
            throw new RecordNotFoundException("Not found projects");
    }

    public void delete(Long id) {
        Optional<Project> project = projectRepo.findById(id);
        if (!project.isEmpty() && project.isPresent())
            projectRepo.delete(project.get());
        else
            throw new RecordNotFoundException("Not found employee");
    }

}
