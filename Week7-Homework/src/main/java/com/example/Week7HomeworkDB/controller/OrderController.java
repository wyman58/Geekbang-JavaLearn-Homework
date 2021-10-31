package com.example.Week7HomeworkDB.controller;


import com.example.Week7HomeworkDB.datasource.ClientDatabase;
import com.example.Week7HomeworkDB.datasource.ClientDatabaseContextHolder;
import com.example.Week7HomeworkDB.entity.Order;
import com.example.Week7HomeworkDB.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.Optional;

@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping(path = "1MOrder")
    public void insert1MOrder(){
        long currentTime = System.currentTimeMillis();
        useJDBC();
        System.out.println("Total Time: " + (System.currentTimeMillis() - currentTime) + "ms");
    }

    //The result is around 13s for 1mil records.
    //need to adjust the max_allowed_packet
    //The worst result is by using Hibernation Execute the SQL one by one
    //The second worse is by using batchUpdate and JDBCTemplate
    private void useJDBC() {
        String insertStatement = "insert into orders values (?, 1, 1, '2021-10-29', 1, '2021-10-29', '2021-10-29')";
        Connection connection = null;
        PreparedStatement ps = null;
        StringBuffer sb = new StringBuffer(insertStatement);
        for (int i = 1; i< 1000000; i++){
            sb.append(",(?, 1, 1, '2021-10-29', 1, '2021-10-29', '2021-10-29')");
        }
        sb.append(";");
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sb.toString());
            for (int i = 1; i <=1000000; i++) {
                ps.setLong(i, (long)i);
            }
            ps.execute();
            connection.commit();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
	}

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public void getOrder(@PathVariable("id") Long id){
        ClientDatabaseContextHolder.set(ClientDatabase.CLIENT_B);
        Optional<Order> order = orderRepository.findById(id);
        System.out.println(order);
        ClientDatabaseContextHolder.clear();
    }
}
