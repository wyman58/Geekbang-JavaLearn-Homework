package com.example.Week8Homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
@EnableTransactionManagement
//@ComponentScan(basePackages = "org.dromara.hmily.*")
public class Week8HomeworkOrderingApplication {


	public static void main(String[] args) {

		SpringApplication.run(Week8HomeworkOrderingApplication.class, args);
	}

}
