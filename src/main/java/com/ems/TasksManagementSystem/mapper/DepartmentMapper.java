package com.ems.TasksManagementSystem.mapper;

import com.ems.TasksManagementSystem.dto.DepartmentDto;
import com.ems.TasksManagementSystem.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = EmplyeeMapper.class)
public interface DepartmentMapper {



    @Mapping(target = "employee",ignore = true)
    @Mapping(target = "manager",source = "manager.name")
    DepartmentDto mapToDTO(Department department);


    List<DepartmentDto> mapToDTO(List<Department> departments);
}
