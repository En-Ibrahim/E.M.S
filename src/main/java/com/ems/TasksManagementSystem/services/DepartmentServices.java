package com.ems.TasksManagementSystem.services;

import com.ems.TasksManagementSystem.dto.DepartmentDto;
import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.mapper.DepartmentMapper;
import com.ems.TasksManagementSystem.repo.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServices {


    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private final DepartmentMapper mapper;



    public DepartmentDto addDepartment(Department department) {

        return mapper.mapToDTO(departmentRepo.save(department));
    }

    public DepartmentDto updateDepartment(Department entity) {
        Optional<Department> employee = departmentRepo.findById(entity.getDept_id());
        if (!employee.isEmpty() && employee.isPresent())
            return mapper.mapToDTO(departmentRepo.save(employee.get()));
        else
            throw new IllegalStateException("Not found employee");
    }

    public DepartmentDto findById(Long id) {
        return mapper.mapToDTO(departmentRepo.findById(id).orElseThrow());
    }


    public List<DepartmentDto> findAll() {
        return mapper.mapToDTO(departmentRepo.findAll());
    }

    public void delete(Long id) {
        Optional<Department> department = departmentRepo.findById(id);
        if (!department.isEmpty() && department.isPresent())
            departmentRepo.delete(department.get());
        else
            throw new IllegalStateException("Not found employee");
    }

}
