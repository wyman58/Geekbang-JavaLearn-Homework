package com.example.Week8Homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableEurekaClient
@SpringBootApplication
@EnableJpaAuditing
public class Week8HomeworkShippingApplication {


	public static void main(String[] args) {

		SpringApplication.run(Week8HomeworkShippingApplication.class, args);
	}

}
