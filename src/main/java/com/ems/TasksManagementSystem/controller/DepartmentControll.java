package com.ems.TasksManagementSystem.controller;


import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.services.DepartmentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DepartmentControll {
    @Autowired
    private DepartmentServices services;

    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(services.addDepartment(department));
    }

    @PutMapping
    public ResponseEntity<?> updateDepartment(Department department) {
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
    public ResponseEntity<?> delete(Long id) {
        services.delete(id);
        return ResponseEntity.ok(null);
    }


}
