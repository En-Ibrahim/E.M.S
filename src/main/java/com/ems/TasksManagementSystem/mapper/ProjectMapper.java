package com.ems.TasksManagementSystem.mapper;

import com.ems.TasksManagementSystem.dto.ProjectDto;
import com.ems.TasksManagementSystem.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {




    ProjectDto mapToDTO(Project project);

    Project mapToEntity(ProjectDto dto);

    List<ProjectDto> mapToDTO(List<Project> projects);

}
