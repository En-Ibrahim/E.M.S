package com.ems.TasksManagementSystem.services;

import com.ems.TasksManagementSystem.dto.DepartmentDto;
import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.exception.BadRequestException;
import com.ems.TasksManagementSystem.exception.RecordNotFoundException;
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
        if(department!=null && department.getName()!=null )
            return mapper.mapToDTO(departmentRepo.save(department));
        else
             throw new BadRequestException("Entry correct data");
    }

    public DepartmentDto updateDepartment(Department entity) {
        Optional<Department> employee = departmentRepo.findById(entity.getDept_id());
        if (!employee.isEmpty() && employee.isPresent())
            return mapper.mapToDTO(departmentRepo.save(employee.get()));
        else
            throw new RecordNotFoundException("Not found Department , Please check it again");
    }

    public DepartmentDto findById(Long id) {
        Optional<Department> optional= departmentRepo.findById(id);
        if (optional.isEmpty()&& optional!=null)
            return mapper.mapToDTO(optional.get());
        else
            throw new RecordNotFoundException("Npt found Department");
    }


    public List<DepartmentDto> findAll() {
        List<Department> optionals = departmentRepo.findAll();
        if (optionals!=null)
            return mapper.mapToDTO(departmentRepo.findAll());
        else
            throw new RecordNotFoundException("Not Found Departments ");
    }

    public void delete(Long id) {
        Optional<Department> department = departmentRepo.findById(id);
        if (!department.isEmpty() && department.isPresent())
            departmentRepo.delete(department.get());
        else
            throw new RecordNotFoundException("Not found employee");
    }

}
