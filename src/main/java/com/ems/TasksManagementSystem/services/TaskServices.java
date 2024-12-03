package com.ems.TasksManagementSystem.services;

import com.ems.TasksManagementSystem.entity.Task;
import com.ems.TasksManagementSystem.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServices {

    @Autowired
    private TaskRepo taskRepo;


    public Task addTask(Task task){
        return taskRepo.save(task);
    }


    public Task updateTask(Task task){
        Optional<Task> entity= taskRepo.findById(task.getTask_id());
        if(!entity.isEmpty() && entity.isPresent())
            return taskRepo.save(entity.get());
        else
            throw new IllegalStateException("Not found task");
    }

    public Task findByID(Long id){
        return taskRepo.findById(id).orElseThrow();
    }

    public List<Task> findAll(){
        return taskRepo.findAll();
    }

    public void delete(Long id){
        taskRepo.deleteById(id);
    }

    
}
