package com.ems.TasksManagementSystem.services;


import com.ems.TasksManagementSystem.dto.EmployeeDto;
import com.ems.TasksManagementSystem.dto.EmployeeDtoRequest;
import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.entity.Employee;
import com.ems.TasksManagementSystem.exception.BadRequestException;
import com.ems.TasksManagementSystem.exception.DuplicatedErrorException;
import com.ems.TasksManagementSystem.exception.RecordNotFoundException;
import com.ems.TasksManagementSystem.mapper.EmployeeMapper;
import com.ems.TasksManagementSystem.mapper.EmployeeRequestMapper;
import com.ems.TasksManagementSystem.repo.DepartmentRepo;
import com.ems.TasksManagementSystem.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final EmployeeMapper mapper;

    @Autowired
    private final EmployeeRequestMapper requestMapper;


    private final Logger logger = LoggerFactory.getLogger(EmployeeServices.class);


    public EmployeeDtoRequest addEmployee(EmployeeDtoRequest employeeDto) {

        Employee employee = requestMapper.mapToEntity(employeeDto);
        Department department = departmentRepo.findByName(employee.getDepartment().getName()).orElseThrow();
        Optional<Employee> optional = employeeRepo.findByEmail(employee.getEmail());
        employee.setDepartment(department);

        validateEmployeeData(employee);

        if (optional.isPresent()) {
            logger.error("The Employee is duplicate");
            throw new DuplicatedErrorException("The Employee is duplicate");
        }
        return requestMapper.mapToDTO(employeeRepo.save(employee));
    }

    private void validateEmployeeData(Employee employee) {
        if (employee.getFullName() == null || employee.getFullName().isBlank()) {
            throw new BadRequestException("Full name is required");
        }
        if (employee.getEmail() == null || employee.getEmail().isBlank()) {
            throw new BadRequestException("Email is required");
        }
        if (employee.getDepartment() == null || employee.getDepartment().getName() == null) {
            throw new BadRequestException("Department name is required");
        }
    }

    public EmployeeDtoRequest updateEmployee(Employee entity) {

        Optional<Employee> employee = employeeRepo.findById(entity.getEmp_id());
        if (!employee.isEmpty() && employee.isPresent()) {
            return requestMapper.mapToDTO(employeeRepo.save(entity));
        } else {
            logger.error("Not found Employee");
            throw new RecordNotFoundException("Not found Employee");
        }
    }

    public EmployeeDto findById(Long id) {
        Optional<Employee> optional = employeeRepo.findById(id);
        if (optional.isPresent() && !optional.isEmpty())
            return mapper.mapToDTO(optional.get());
        else {
            logger.error("Not found Employee");
            throw new RecordNotFoundException("Not found Employee");
        }
    }
    public EmployeeDto findByEmail(String email) {
        Optional<Employee> optional = employeeRepo.findByEmail(email);
        if (optional.isPresent() && !optional.isEmpty())
            return mapper.mapToDTO(optional.get());
        else {
            logger.error("Not found Employee");
            throw new RecordNotFoundException("Not found Employee");
        }
    }

    public List<EmployeeDto> findAll() {
        if (employeeRepo.findAll() != null)
            return mapper.mapToDTO(employeeRepo.findAll());
        else {
            logger.error("Not found Employee");
            throw new RecordNotFoundException("Not found Employee");
        }
    }

    public List<EmployeeDto> findAllEmployeeByDepartment(String department) {

        Optional<Department> department1 = departmentRepo.findByName(department);

        List<Employee> employees = employeeRepo.findAllByDepartment(department1.get().getName());

        if (!department1.isEmpty() && department1.isPresent() && department != null)
            if (employees != null)
                return mapper.mapToDTO(employees);
            else {
                logger.error("Not Found Employees in this department");
                throw new RecordNotFoundException("Not Found Employees in this department");
            }
        else {
            logger.error("not found Department");
            throw new RecordNotFoundException("not found Department");
        }
    }

    public void delete(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (!employee.isEmpty() && employee.isPresent())
            employeeRepo.delete(employee.get());
        else
            throw new RecordNotFoundException("Not found employee");
    }

}
