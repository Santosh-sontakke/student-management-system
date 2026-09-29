package com.santosh.studentmanagementsystem.service;

import com.santosh.studentmanagementsystem.dto.CourseRequest;
import com.santosh.studentmanagementsystem.model.Course;
import com.santosh.studentmanagementsystem.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository repository;
    public CourseService(CourseRepository repository) { this.repository = repository; }
    public List<Course> findAll() { return repository.findAll(); }
    public Course findById(Long id) { return repository.findById(id).orElseThrow(() -> new NotFoundException("Course " + id + " was not found")); }
    public Course create(CourseRequest request) { return save(new Course(), request); }
    public Course update(Long id, CourseRequest request) { return save(findById(id), request); }
    private Course save(Course course, CourseRequest request) {
        course.setName(request.name().trim()); course.setCode(request.code().trim().toUpperCase());
        course.setDescription(request.description()); if (request.active() != null) course.setActive(request.active());
        return repository.save(course);
    }
    public void delete(Long id) { repository.delete(findById(id)); }
}
