package com.santosh.studentmanagementsystem.dto;

public class StudentCreateResponse {

    private Long id;
    private String name;
    private String email;
    private Long courseId;
    private String courseName;

    public StudentCreateResponse() {
    }

    public StudentCreateResponse(
            Long id,
            String name,
            String email,
            Long courseId,
            String courseName
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courseId = courseId;
        this.courseName = courseName;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}