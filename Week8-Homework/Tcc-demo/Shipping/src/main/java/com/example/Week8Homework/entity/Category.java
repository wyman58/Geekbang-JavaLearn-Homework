package com.example.Week8Homework.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Categories")
@EntityListeners(AuditingEntityListener.class)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryID;

    @Column(name = "CategoryName")
    private String categoryName;

    private String description;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate changedDate;
}
