package com.ems.TasksManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dept_id;

    private String name;

    @OneToOne
    private Employee manager;


}
