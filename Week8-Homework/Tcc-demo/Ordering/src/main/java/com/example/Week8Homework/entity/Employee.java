package com.example.Week8Homework.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Employees")
@EntityListeners(AuditingEntityListener.class)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeID;

    private String lastName;

    private String firstName;

    private LocalDate birthday;

    private String photoPath;

    private String notes;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate changedDate;
}
