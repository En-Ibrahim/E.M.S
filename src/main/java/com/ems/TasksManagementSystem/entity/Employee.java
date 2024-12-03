package com.ems.TasksManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emp_id;

    private String name;
    @Email
    private String email;
    private String position;

    @ManyToOne
    private Department department;

}
