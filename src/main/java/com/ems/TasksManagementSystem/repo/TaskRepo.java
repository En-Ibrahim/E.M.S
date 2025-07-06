package com.ems.TasksManagementSystem.repo;

import com.ems.TasksManagementSystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {


    Optional<Task> findByName(String name);
}
