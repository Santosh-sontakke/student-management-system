package com.santosh.studentmanagementsystem.dto;

public class StudentCreateRequest {

    private String name;
    private String email;
    private Long courseId;

    public StudentCreateRequest() {
    }

    public StudentCreateRequest(String name, String email, Long courseId) {
        this.name = name;
        this.email = email;
        this.courseId = courseId;
    }

    public String getName() {
        return name;    
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}