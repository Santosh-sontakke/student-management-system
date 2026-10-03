package com.santosh.studentmanagementsystem.controller;

import com.santosh.studentmanagementsystem.dto.StudentCreateRequest;
import com.santosh.studentmanagementsystem.dto.StudentCreateResponse;
import com.santosh.studentmanagementsystem.model.Student;
import com.santosh.studentmanagementsystem.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentCreateResponse createStudent(
            @RequestBody StudentCreateRequest request
    ) {
        return studentService.createStudent(request);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }
}