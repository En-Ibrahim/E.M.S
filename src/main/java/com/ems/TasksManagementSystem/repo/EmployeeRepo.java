package com.ems.TasksManagementSystem.repo;

import com.ems.TasksManagementSystem.entity.Department;
import com.ems.TasksManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query(value = "select e from Employee e where e.department.name=?1", nativeQuery = false)
    List<Employee> findAllByDepartment(String department);

    Optional<Employee> findByEmail(String email);


}
