package com.example.StarterDemo;

import com.example.StarterDemo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(StudentConfiguration.class)
@EnableConfigurationProperties(StudentProperty.class)
public class StudentAutoConfiguration {
    @Autowired
    StudentProperty studentProperty;
    @Autowired
    StudentConfiguration studentConfiguration;

    @Bean
    public Student getStudent(){
        return new Student(studentConfiguration.name + "-" +  studentProperty.getA());
    }
}
