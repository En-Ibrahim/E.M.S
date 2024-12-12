package com.ems.TasksManagementSystem.mapper;

import com.ems.TasksManagementSystem.dto.TaskDto;
import com.ems.TasksManagementSystem.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TaskMapper {


    @Mapping(target = "project",source = "project.name")
    @Mapping(target = "belongTo",source = "belongTo.name")
    TaskDto mapToDTO(Task task);

    List<TaskDto> mapToDTO(List<Task> task);
}
