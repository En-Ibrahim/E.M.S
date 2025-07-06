package com.ems.TasksManagementSystem.services;

import com.ems.TasksManagementSystem.dto.TaskDto;
import com.ems.TasksManagementSystem.entity.Employee;
import com.ems.TasksManagementSystem.entity.Project;
import com.ems.TasksManagementSystem.entity.Task;
import com.ems.TasksManagementSystem.exception.BadRequestException;
import com.ems.TasksManagementSystem.exception.RecordNotFoundException;
import com.ems.TasksManagementSystem.mapper.TaskMapper;
import com.ems.TasksManagementSystem.repo.EmployeeRepo;
import com.ems.TasksManagementSystem.repo.ProjectRepo;
import com.ems.TasksManagementSystem.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private ProjectRepo projectRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private final TaskMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(TaskServices.class);

    public TaskDto addTask(TaskDto dto) {

        Task task = mapper.mapToEntity(dto);

        if (dto.getProject() != null) {
            Optional<Project> project = projectRepo.findById(dto.getProject());
            if (project.isPresent())
                task.setProject(project.get());
            else
                throw new RecordNotFoundException("Not found project");
        }
        else
            throw new RecordNotFoundException("Not found Project");

        if (dto.getAssign_to() != null) {
            Optional<Employee> employee = employeeRepo.findById(dto.getAssign_to());
            if (employee.isPresent())
                task.setAssign_to(employee.get());
            else
                throw new RecordNotFoundException("not found Employee");
        }
        else
            task.setAssign_to(null);

        if (task != null && task.getName() != null)
            return mapper.mapToDTO(taskRepo.save(task));
        else {
            logger.error("Entry correct data");
            throw new BadRequestException("Entry correct data");
        }
    }


    public TaskDto updateTask(TaskDto dto) {
        long start = System.currentTimeMillis();
        Optional<Task> entity = taskRepo.findById(dto.getTask_id());
        if (!entity.isEmpty()) {
            Optional<Project> project = projectRepo.findById(dto.getProject());
            Optional<Employee> employee = employeeRepo.findById(dto.getAssign_to());

            entity.get().setProject(project.get());
            entity.get().setAssign_to(employee.get());

            long end = System.currentTimeMillis() - start;
            System.out.println("the time take: " + end); // without index search time =244 ms and with index time =11 ms
            return mapper.mapToDTO(taskRepo.save(entity.get()));
        } else {
            logger.error("Not found Task");
            throw new RecordNotFoundException("Not found Task");
        }


    }

    public TaskDto findByID(Long id) {
        if (taskRepo.findById(id).isPresent() && !taskRepo.findById(id).isEmpty())
            return mapper.mapToDTO(taskRepo.findById(id).orElseThrow());
        else {
            logger.error("Not found Task");
            throw new RecordNotFoundException("Not found Task");
        }
    }

    public List<TaskDto> findAll() {
        if (taskRepo.findAll() != null)
            return mapper.mapToDTO(taskRepo.findAll());
        else {
            logger.error("Not found Task");
            throw new RecordNotFoundException("Not found Task");
        }
    }

    public void delete(Long id) {

        Optional<Task> entity = taskRepo.findById(id);
        if (!entity.isEmpty() && entity.isPresent())
            taskRepo.deleteById(id);
        else {
            logger.error("Not found Task");
            throw new RecordNotFoundException("Not found Task");
        }

    }


}
