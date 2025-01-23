package com.ems.TasksManagementSystem.services;

import com.ems.TasksManagementSystem.dto.TaskDto;
import com.ems.TasksManagementSystem.entity.Task;
import com.ems.TasksManagementSystem.exception.BadRequestException;
import com.ems.TasksManagementSystem.exception.RecordNotFoundException;
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
        if (task!=null && task.getName()!=null)
            return mapper.mapToDTO(taskRepo.save(task));
        else
            throw new BadRequestException("Entry correct data");
    }


    public TaskDto updateTask(Task task) {
        Optional<Task> entity = taskRepo.findById(task.getTask_id());
        if (!entity.isEmpty() && entity.isPresent())
            return mapper.mapToDTO(taskRepo.save(entity.get()));
        else
            throw new RecordNotFoundException("Not found task");
    }

    public TaskDto findByID(Long id) {
        if (taskRepo.findById(id).isPresent() && !taskRepo.findById(id).isEmpty())
            return mapper.mapToDTO(taskRepo.findById(id).orElseThrow());
        else
            throw new RecordNotFoundException("Not found the task");
    }

    public List<TaskDto> findAll() {
        if(taskRepo.findAll()!=null)
            return mapper.mapToDTO(taskRepo.findAll());
        else
            throw new RecordNotFoundException("Not found tasks");
    }

    public void delete(Long id) {

        Optional<Task> entity = taskRepo.findById(id);
        if (!entity.isEmpty() && entity.isPresent())
            taskRepo.deleteById(id);
        else
            throw new RecordNotFoundException("Not found task");

    }


}
