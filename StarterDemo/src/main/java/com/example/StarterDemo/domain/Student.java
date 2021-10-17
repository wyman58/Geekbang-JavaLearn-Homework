package com.example.StarterDemo.domain;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    public Student(String name){
        this.name = name;
    }
}
