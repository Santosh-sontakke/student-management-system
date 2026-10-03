package com.santosh.studentmanagementsystem.model;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name, email;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Student(){}
    public Student(Long id, String name, String email, Course course){
        this.id=id;
        this.name=name;
        this.course=course;
        this.email=email;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
