package com.ems.TasksManagementSystem.mapper;

import com.ems.TasksManagementSystem.dto.TaskDto;
import com.ems.TasksManagementSystem.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TaskMapper {


    @Mapping(target = "project",source = "project.project_id")
    @Mapping(target = "assign_to",source = "assign_to.emp_id")
    TaskDto mapToDTO(Task task);

    @Mapping(target = "project.project_id",source = "project")
    @Mapping(target = "assign_to.emp_id",source = "assign_to")
    Task mapToEntity(TaskDto dto);
    List<TaskDto> mapToDTO(List<Task> task);
}
