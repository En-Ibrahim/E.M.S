package com.ems.TasksManagementSystem.repo;

import com.ems.TasksManagementSystem.entity.Employee;
import com.ems.TasksManagementSystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {



}
