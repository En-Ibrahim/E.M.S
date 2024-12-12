package com.ems.TasksManagementSystem.services;

import com.ems.TasksManagementSystem.dto.TaskDto;
import com.ems.TasksManagementSystem.entity.Task;
import com.ems.TasksManagementSystem.mapper.TaskMapper;
import com.ems.TasksManagementSystem.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServices {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private final TaskMapper mapper;

    public TaskDto addTask(Task task) {
        return mapper.mapToDTO(taskRepo.save(task));
    }


    public TaskDto updateTask(Task task) {
        Optional<Task> entity = taskRepo.findById(task.getTask_id());
        if (!entity.isEmpty() && entity.isPresent())
            return mapper.mapToDTO(taskRepo.save(entity.get()));
        else
            throw new IllegalStateException("Not found task");
    }

    public TaskDto findByID(Long id) {
        return mapper.mapToDTO(taskRepo.findById(id).orElseThrow());
    }

    public List<TaskDto> findAll() {
        return mapper.mapToDTO(taskRepo.findAll());
    }

    public void delete(Long id) {

        Optional<Task> entity = taskRepo.findById(id);
        if (!entity.isEmpty() && entity.isPresent())
            taskRepo.deleteById(id);
        else
            throw new IllegalStateException("Not found task");

    }


}
