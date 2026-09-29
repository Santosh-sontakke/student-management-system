package com.santosh.studentmanagementsystem.controller;

import com.santosh.studentmanagementsystem.model.Student;
import com.santosh.studentmanagementsystem.repository.StudentRepository;
import com.santosh.studentmanagementsystem.service.NotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository students;
    public StudentController(StudentRepository students) { this.students = students; }
    @GetMapping public List<Student> list() { return students.findAll(); }
    @GetMapping("/{id}") public Student get(@PathVariable Long id) { return students.findById(id).orElseThrow(() -> new NotFoundException("Student " + id + " was not found")); }
}
