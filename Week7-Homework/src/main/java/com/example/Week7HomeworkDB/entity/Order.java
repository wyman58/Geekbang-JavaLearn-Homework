package com.example.Week7HomeworkDB.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "Orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    private Long customerID;

    private Long employeeID;

    private LocalDate orderDate;

    private Long shipperID;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate changedDate;
}
