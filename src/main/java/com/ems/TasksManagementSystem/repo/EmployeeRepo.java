package com.ems.TasksManagementSystem.repo;

import com.ems.TasksManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    Optional<List<Employee>> findAllByDepartment(String department);


}
