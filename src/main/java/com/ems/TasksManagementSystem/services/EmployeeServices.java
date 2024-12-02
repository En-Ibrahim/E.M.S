package com.ems.TasksManagementSystem.services;


import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.entity.Employee;
import com.ems.TasksManagementSystem.repo.DepartmentRepo;
import com.ems.TasksManagementSystem.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServices {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    public Employee  addEmployee (Employee employee){
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Employee entity){
        Optional<Employee> employee= employeeRepo.findById(entity.getEmp_id());
        if (!employee.isEmpty() && employee.isPresent())
           return employeeRepo.save(employee.get());
        else
            throw new IllegalStateException("Not found employee");
    }

    public Employee findById(Long id){
        return employeeRepo.findById(id).orElseThrow();
    }

    public Optional<List<Employee>> findAllEmployeeByDepartment(String department){

        Optional<Department> department1= departmentRepo.findByName(department);
        if (!department1.isEmpty()&&department1.isPresent())
            return employeeRepo.findAllByDepartment(department);
        else
            throw new IllegalStateException("not found Department");
    }

    public void delete(Long id){
        Optional<Employee> employee= employeeRepo.findById(id);
        if (!employee.isEmpty() && employee.isPresent())
             employeeRepo.delete(employee.get());
        else
            throw new IllegalStateException("Not found employee");
    }

}
