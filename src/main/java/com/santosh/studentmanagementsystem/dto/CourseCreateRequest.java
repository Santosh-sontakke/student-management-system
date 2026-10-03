package com.santosh.studentmanagementsystem.dto;

public class CourseCreateRequest {

    private String name;

    public CourseCreateRequest() {
    }

    public CourseCreateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}