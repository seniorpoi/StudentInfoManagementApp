package com.example.studentinfomanagementapp;

public class Student {
    private String name;
    private int age;
    private int grade;
    private String major;

    public Student(String name, int age, int grade, String major) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGrade() {
        return grade;
    }

    public String getMajor() {
        return major;
    }
}