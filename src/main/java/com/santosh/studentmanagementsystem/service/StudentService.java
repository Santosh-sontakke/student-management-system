package com.santosh.studentmanagementsystem.service;

import com.santosh.studentmanagementsystem.model.Student;
import com.santosh.studentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository= studentRepository;
    }

    public Student getStudent(Long id){
        return studentRepository.findById(id).orElseThrow();
    }
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
}
