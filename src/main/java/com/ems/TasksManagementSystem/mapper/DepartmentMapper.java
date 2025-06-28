package com.ems.TasksManagementSystem.mapper;

import com.ems.TasksManagementSystem.dto.DepartmentDto;
import com.ems.TasksManagementSystem.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = EmployeeMapper.class)
public interface DepartmentMapper {



//    @Mapping(target = "employee",ignore = true)
    @Mapping(target = "manager",source = "manager.fullName")
    DepartmentDto mapToDTO(Department department);
    @Mapping(target = "manager.fullName",source = "manager")
    Department mapToEntity(DepartmentDto dto);


    List<DepartmentDto> mapToDTO(List<Department> departments);
    List<Department> mapToEntity(List<DepartmentDto> dtos);
}
