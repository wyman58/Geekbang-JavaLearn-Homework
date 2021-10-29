package com.example.Week5HomeworkDB.repository;


import com.example.Week5HomeworkDB.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> { }
