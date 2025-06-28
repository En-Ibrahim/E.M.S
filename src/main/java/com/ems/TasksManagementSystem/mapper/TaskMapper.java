package com.ems.TasksManagementSystem.mapper;

import com.ems.TasksManagementSystem.dto.TaskDto;
import com.ems.TasksManagementSystem.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TaskMapper {


    @Mapping(target = "project",source = "project.name")
    @Mapping(target = "assign_to",source = "assign_to.fullName")
    TaskDto mapToDTO(Task task);

    @Mapping(target = "project.name",source = "project")
    @Mapping(target = "assign_to.fullName",source = "assign_to")
    Task mapToEntity(TaskDto dto);
    List<TaskDto> mapToDTO(List<Task> task);
}
