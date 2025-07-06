package com.ems.TasksManagementSystem.controller;


import com.ems.TasksManagementSystem.dto.DepartmentDto;
import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.mapper.DepartmentMapper;
import com.ems.TasksManagementSystem.services.DepartmentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dept")
@RequiredArgsConstructor
public class DepartmentController {
    @Autowired
    private DepartmentServices services;

    @Autowired
    private DepartmentMapper mapper;

    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody Department department) {

        return ResponseEntity.ok(services.addDepartment(department));
    }

    @PutMapping
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentDto department) {
        return ResponseEntity.ok(services.updateDepartment(department));
    }

    @GetMapping("/id")
    public ResponseEntity<?> findByID(@Param("id") Long id) {
        return ResponseEntity.ok(services.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(services.findAll());
    }


    @DeleteMapping
    public ResponseEntity<?> delete(@Param("id")  Long id) {
        services.delete(id);
        return ResponseEntity.ok(null);
    }


}
