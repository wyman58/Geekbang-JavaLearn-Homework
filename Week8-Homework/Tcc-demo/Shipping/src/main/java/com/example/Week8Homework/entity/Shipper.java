package com.example.Week8Homework.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Shippers")
@EntityListeners(AuditingEntityListener.class)
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shipperID;

    private String shipperName;

    private String phone;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate changedDate;
}
