package com.ems.TasksManagementSystem.services;


import com.ems.TasksManagementSystem.dto.ProjectDto;
import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.entity.Project;
import com.ems.TasksManagementSystem.exception.BadRequestException;
import com.ems.TasksManagementSystem.exception.RecordNotFoundException;
import com.ems.TasksManagementSystem.mapper.ProjectMapper;
import com.ems.TasksManagementSystem.repo.DepartmentRepo;
import com.ems.TasksManagementSystem.repo.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private DepartmentRepo departmentRepo;

    @Autowired
    private final ProjectMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(ProjectServices.class);

    public ProjectDto addProject(ProjectDto dto) {
        if (dto == null || dto.getName() == null || dto.getName().trim().isEmpty()) {
            logger.error("Project name is required");
            throw new BadRequestException("Project name is required");
        }
        
        Project project = mapper.mapToEntity(dto);
        
        // Handle department if provided
        if (dto.getDepartment() != null && !dto.getDepartment().trim().isEmpty()) {
            Optional<Department> dept = departmentRepo.findByName(dto.getDepartment());
            if (dept.isPresent()) {
                project.setDepartment(dept.get());
            } else {
                logger.error("Department not found: " + dto.getDepartment());
                throw new BadRequestException("Department not found: " + dto.getDepartment());
            }
        }
        
        return mapper.mapToDTO(projectRepo.save(project));
    }

    public ProjectDto updateProject(ProjectDto entity) {
        if (entity == null || entity.getProject_id() == null) {
            logger.error("Project ID is required for update");
            throw new BadRequestException("Project ID is required for update");
        }
        
        Optional<Project> existingProject = projectRepo.findById(entity.getProject_id());
        if (existingProject.isEmpty()) {
            logger.error("Project not found with ID: " + entity.getProject_id());
            throw new RecordNotFoundException("Project not found with ID: " + entity.getProject_id());
        }
        
        Project projectToUpdate = existingProject.get();
        
        // Update fields if provided
        if (entity.getName() != null && !entity.getName().trim().isEmpty()) {
            projectToUpdate.setName(entity.getName());
        }
        if (entity.getDescription() != null) {
            projectToUpdate.setDescription(entity.getDescription());
        }
        if (entity.getDepartment() != null) {
            Optional<Department> dept=departmentRepo.findByName(entity.getDepartment());
            projectToUpdate.setDepartment(dept.get());
        }
        
        return mapper.mapToDTO(projectRepo.save(projectToUpdate));
    }

    public ProjectDto findById(Long id) {
        Optional<Project> optional = projectRepo.findById(id);
        if(!optional.isEmpty() && optional.isPresent())
            return mapper.mapToDTO(optional.get());
        else {
            logger.error("Not found Project");
            throw new RecordNotFoundException("Not found Project");
        }
    }


    public List<ProjectDto> findAll() {
        List<Project> projects = projectRepo.findAll();
        if(projects.isEmpty()) {
            logger.warn("No projects found");
            return mapper.mapToDTO(projects);
        }
        return mapper.mapToDTO(projects);
    }

    public void delete(Long id) {
        Optional<Project> project = projectRepo.findById(id);
        if (!project.isEmpty() && project.isPresent())
            projectRepo.delete(project.get());
        else {
            logger.error("Not found Project");
            throw new RecordNotFoundException("Not found Project");
        }
    }

}
