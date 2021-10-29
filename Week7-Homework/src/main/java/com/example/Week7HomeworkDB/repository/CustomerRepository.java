package com.example.Week7HomeworkDB.repository;


import com.example.Week7HomeworkDB.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> { }
