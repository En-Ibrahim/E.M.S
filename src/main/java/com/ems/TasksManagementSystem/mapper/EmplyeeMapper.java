package com.ems.TasksManagementSystem.mapper;

import com.ems.TasksManagementSystem.dto.EmployeeDto;
import com.ems.TasksManagementSystem.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface EmplyeeMapper {



    @Mapping(target = "department",source = "department.name")
    EmployeeDto mapToDTO(Employee employee);


    List<EmployeeDto> mapToDTO(List<Employee> employees);
}
