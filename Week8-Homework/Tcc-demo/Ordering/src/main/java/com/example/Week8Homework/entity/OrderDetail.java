package com.example.Week8Homework.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "OrderDetails")
@EntityListeners(AuditingEntityListener.class)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderDetailID;


    private Long orderID;

    private Long productID;

    private Long quantity;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate changedDate;

}
