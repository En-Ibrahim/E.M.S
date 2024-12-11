package com.ems.TasksManagementSystem.controller;



import com.ems.TasksManagementSystem.entity.Task;
import com.ems.TasksManagementSystem.services.TaskServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskControll {
    @Autowired
    private TaskServices services;

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody Task task){
        return ResponseEntity.ok(services.addTask(task));
    }
    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody Task task){
        return ResponseEntity.ok(services.updateTask(task));
    }
    @GetMapping("/id")
    public ResponseEntity<?> findByID(@Param("id") Long id){
        return ResponseEntity.ok(services.findByID(id));
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(services.findAll());
    }


    @DeleteMapping
    public ResponseEntity<?> delete(Long id){
        services.delete(id);
        return ResponseEntity.ok(null);
    }


}
