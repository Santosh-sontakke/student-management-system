package com.santosh.studentmanagementsystem.controller;

import com.santosh.studentmanagementsystem.model.Student;
import com.santosh.studentmanagementsystem.service.HelloService;
import com.santosh.studentmanagementsystem.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    StudentService studentService;
    public StudentController(HelloService helloService, StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }
    @PostMapping
    public Student creatStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
}
