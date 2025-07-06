package com.ems.TasksManagementSystem.mapper;

import com.ems.TasksManagementSystem.dto.ProjectDto;
import com.ems.TasksManagementSystem.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProjectMapper {

    @Mapping(target = "department", source = "department.name")
    ProjectDto mapToDTO(Project project);

    @Mapping(target = "department.name", source = "department")
    Project mapToEntity(ProjectDto dto);

    List<ProjectDto> mapToDTO(List<Project> projects);

}
