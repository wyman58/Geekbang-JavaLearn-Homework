package com.example.Week7HomeworkDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
@EnableJpaAuditing
public class Week7HomeworkDbApplication {


	public static void main(String[] args) {

		SpringApplication.run(Week7HomeworkDbApplication.class, args);
	}

}
