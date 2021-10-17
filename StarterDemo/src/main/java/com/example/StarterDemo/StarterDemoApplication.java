package com.example.StarterDemo;

import com.example.StarterDemo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StarterDemoApplication {

	@Autowired
	Student student;


	@Bean
	public void printInfo(){
		System.out.println(student.getName());
	}

	public static void main(String[] args) {

		SpringApplication.run(StarterDemoApplication.class, args);
	}

}
