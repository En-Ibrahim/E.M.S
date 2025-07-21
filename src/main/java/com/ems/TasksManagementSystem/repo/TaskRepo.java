package com.ems.TasksManagementSystem.repo;

import com.ems.TasksManagementSystem.entity.State;
import com.ems.TasksManagementSystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.project_id = :projectId")
    int countByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.project_id = :projectId AND t.state = :state")
    int countByProjectIdAndState(@Param("projectId") Long projectId, @Param("state") State state);
    Optional<Task> findByName(String name);
}
