package com.ems.TasksManagementSystem.repo;

import com.ems.TasksManagementSystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

    Optional<Project> findByName(String  name);
}
