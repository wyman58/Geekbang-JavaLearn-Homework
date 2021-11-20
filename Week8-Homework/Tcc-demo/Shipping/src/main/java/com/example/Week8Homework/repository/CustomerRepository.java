package com.example.Week8Homework.repository;


import com.example.Week8Homework.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> { }
