package com.ems.TasksManagementSystem.controller;


import com.ems.TasksManagementSystem.entity.Employee;
import com.ems.TasksManagementSystem.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeControll {

    @Autowired
    private EmployeeServices services;

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(services.addEmployee(employee));
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(services.updateEmployee(employee));
    }

    @GetMapping("/id")
    public ResponseEntity<?> findByID(@Param("id") Long id) {
        return ResponseEntity.ok(services.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(services.findAll());
    }

    @GetMapping("/dept_empl")
    public ResponseEntity<?> findAllEmployeeByDepartment(@Param("department") String department) {
        return ResponseEntity.ok(services.findAllEmployeeByDepartment(department));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Long id) {
        services.delete(id);
        return ResponseEntity.ok(null);
    }


}
