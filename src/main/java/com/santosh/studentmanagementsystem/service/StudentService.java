package com.santosh.studentmanagementsystem.service;

import com.santosh.studentmanagementsystem.dto.StudentCreateRequest;
import com.santosh.studentmanagementsystem.dto.StudentCreateResponse;
import com.santosh.studentmanagementsystem.model.Course;
import com.santosh.studentmanagementsystem.model.Student;
import com.santosh.studentmanagementsystem.repository.CourseRepository;
import com.santosh.studentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public StudentCreateResponse createStudent(StudentCreateRequest request) {

        Course course = courseRepository
                .findById(request.getCourseId())
                .orElseThrow();

        Student student = new Student();

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setCourse(course);

        Student savedStudent = studentRepository.save(student);

        return new StudentCreateResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getEmail(),
                savedStudent.getCourse().getId(),
                savedStudent.getCourse().getName()
        );
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }
}
