package com.ems.TasksManagementSystem.services;


import com.ems.TasksManagementSystem.dto.EmployeeDto;
import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.entity.Employee;

import com.ems.TasksManagementSystem.exception.BadRequestException;
import com.ems.TasksManagementSystem.exception.RecordNotFoundException;
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
        if(employee!=null )
            return mapper.mapToDTO(employeeRepo.save(employee));
        else
            throw new BadRequestException("Entry Correct data");
    }

    public EmployeeDto updateEmployee(Employee entity) {

        Optional<Employee> employee = employeeRepo.findById(entity.getEmp_id());
        if (!employee.isEmpty() && employee.isPresent()) {
            return mapper.mapToDTO(employeeRepo.save(employee.get()));
        }
        else
            throw new RecordNotFoundException("Not found Employee");
    }

    public EmployeeDto findById(Long id) {
        Optional<Employee> optional = employeeRepo.findById(id);
        if (optional.isPresent()&& !optional.isEmpty())
            return mapper.mapToDTO(optional.get());
        else
            throw new RecordNotFoundException("Not found Employee");
    }

    public List<EmployeeDto> findAll() {
        if(employeeRepo.findAll()!=null)
            return mapper.mapToDTO(employeeRepo.findAll());
        else
            throw new RecordNotFoundException("Not found Employees");
    }

    public List<EmployeeDto> findAllEmployeeByDepartment(String department) {

        Optional<Department> department1 = departmentRepo.findByName(department);
        if (!department1.isEmpty() && department1.isPresent() && department!=null)
            if (employeeRepo.findAllByDepartment(department)!=null)
                return mapper.mapToDTO(employeeRepo.findAllByDepartment(department));
            else
                throw new RecordNotFoundException("Not Found Employees in this department");
        else
            throw new RecordNotFoundException("not found Department");
    }

    public void delete(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (!employee.isEmpty() && employee.isPresent())
            employeeRepo.delete(employee.get());
        else
            throw new IllegalStateException("Not found employee");
    }

}
