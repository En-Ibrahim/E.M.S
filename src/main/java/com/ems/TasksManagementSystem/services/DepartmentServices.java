package com.ems.TasksManagementSystem.services;


import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.entity.Employee;
import com.ems.TasksManagementSystem.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServices {


    @Autowired
    private DepartmentRepo departmentRepo;

    public Department  addDepartment (Department department){
        return departmentRepo.save(department);
    }

    public Department updateDepartment(Department entity){
        Optional<Department> employee= departmentRepo.findById(entity.getDept_id());
        if (!employee.isEmpty() && employee.isPresent())
           return departmentRepo.save(employee.get());
        else
            throw new IllegalStateException("Not found employee");
    }

    public Department findById(Long id){
        return departmentRepo.findById(id).orElseThrow();
    }


    public List<Department> findAll(){
        return departmentRepo.findAll();
    }

    public void delete(Long id){
        Optional<Department> department= departmentRepo.findById(id);
        if (!department.isEmpty() && department.isPresent())
            departmentRepo.delete(department.get());
        else
            throw new IllegalStateException("Not found employee");
    }

}
