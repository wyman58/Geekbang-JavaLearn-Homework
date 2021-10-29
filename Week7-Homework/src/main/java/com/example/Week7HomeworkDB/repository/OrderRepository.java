package com.example.Week7HomeworkDB.repository;

import com.example.Week7HomeworkDB.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
