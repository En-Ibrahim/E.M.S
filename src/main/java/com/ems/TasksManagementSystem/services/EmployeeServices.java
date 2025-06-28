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

        Employee employee=requestMapper.mapToEntity(employeeDto);

        if (employee != null && employee.getFullName() != null && employee.getEmail() != null) {
            if (employeeRepo.findByEmail(employee.getEmail()).isEmpty() && !employeeRepo.findByEmail(employee.getEmail()).isPresent()) {
                return requestMapper.mapToDTO(employeeRepo.save(employee));
            } else {
                logger.error("The Employee is duplicate");
                throw new DuplicatedErrorException("The Employee is duplicate");
            }
        } else {
            logger.error("Entry Correct data");
            throw new BadRequestException("Entry Correct data");
        }
    }

    public EmployeeDto updateEmployee(Employee entity) {

        Optional<Employee> employee = employeeRepo.findById(entity.getEmp_id());
        if (!employee.isEmpty() && employee.isPresent()) {
            return mapper.mapToDTO(employeeRepo.save(employee.get()));
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
        if (!department1.isEmpty() && department1.isPresent() && department != null)
            if (employeeRepo.findAllByDepartment(department) != null)
                return mapper.mapToDTO(employeeRepo.findAllByDepartment(department));
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
