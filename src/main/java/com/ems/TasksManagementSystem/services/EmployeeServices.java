package com.ems.TasksManagementSystem.services;


import com.ems.TasksManagementSystem.dto.EmployeeDto;
import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.entity.Employee;

import com.ems.TasksManagementSystem.mapper.EmplyeeMapper;
import com.ems.TasksManagementSystem.repo.DepartmentRepo;
import com.ems.TasksManagementSystem.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServices {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private final EmplyeeMapper mapper;

    public EmployeeDto addEmployee(Employee employee) {
        EmployeeDto dto=  mapper.mapToDTO(employeeRepo.save(employee));
        return dto;
    }

    public EmployeeDto updateEmployee(Employee entity) {

        Optional<Employee> employee = employeeRepo.findById(entity.getEmp_id());
        if (!employee.isEmpty() && employee.isPresent()) {
            EmployeeDto dto = mapper.mapToDTO(employeeRepo.save(employee.get()));
            return dto;
        }
        else
            throw new IllegalStateException("Not found employee");
    }

    public EmployeeDto findById(Long id) {
        EmployeeDto dto = mapper.mapToDTO(employeeRepo.findById(id).orElseThrow());
        return dto;
    }

    public List<EmployeeDto> findAll() {

        return mapper.mapToDTO(employeeRepo.findAll());
    }

    public List<EmployeeDto> findAllEmployeeByDepartment(String department) {

        Optional<Department> department1 = departmentRepo.findByName(department);
        if (!department1.isEmpty() && department1.isPresent() && department!=null)

            return mapper.mapToDTO(employeeRepo.findAllByDepartment(department));
        else
            throw new IllegalStateException("not found Department");
    }

    public void delete(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (!employee.isEmpty() && employee.isPresent())
            employeeRepo.delete(employee.get());
        else
            throw new IllegalStateException("Not found employee");
    }

}
