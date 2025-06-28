package com.ems.TasksManagementSystem.mapper;

import com.ems.TasksManagementSystem.dto.EmployeeDtoRequest;
import com.ems.TasksManagementSystem.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmployeeRequestMapper {

    @Mapping(target = "department",source = "department.name")
    EmployeeDtoRequest mapToDTO(Employee employee);

    @Mapping(target = "department.name",source = "department")
    Employee mapToEntity(EmployeeDtoRequest dtoRequest);

}
