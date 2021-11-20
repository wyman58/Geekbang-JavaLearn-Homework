package com.example.Week8Homework.entity;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Customers")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerID;
    private String customerName;
    private String contactName;
    private String address;
    private String city;
    private String postalCode;
    private String country;
    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate changedDate;
}
