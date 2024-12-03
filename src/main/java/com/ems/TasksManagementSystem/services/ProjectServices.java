package com.ems.TasksManagementSystem.services;


import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.entity.Project;
import com.ems.TasksManagementSystem.repo.DepartmentRepo;
import com.ems.TasksManagementSystem.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServices {


    @Autowired
    private ProjectRepo projectRepo;

    public Project addProject (Project project){
        return projectRepo.save(project);
    }

    public Project updateProject(Project entity){
        Optional<Project> project= projectRepo.findById(entity.getProject_id());
        if (!project.isEmpty() && project.isPresent())
           return projectRepo.save(project.get());
        else
            throw new IllegalStateException("Not found employee");
    }

    public Project findById(Long id){
        return projectRepo.findById(id).orElseThrow();
    }


    public List<Project> findAll(){
        return projectRepo.findAll();
    }

    public void delete(Long id){
        Optional<Project> project= projectRepo.findById(id);
        if (!project.isEmpty() && project.isPresent())
            projectRepo.delete(project.get());
        else
            throw new IllegalStateException("Not found employee");
    }

}
