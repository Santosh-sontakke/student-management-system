package com.santosh.studentmanagementsystem.service;

import com.santosh.studentmanagementsystem.model.Course;
import com.santosh.studentmanagementsystem.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    public Course updateCourse(Course course) {

        if (course.getId() == null) {
            return course;
        }

        Course existingCourse = courseRepository
                .findById(course.getId())
                .orElseThrow();

        if (course.getName() != null) {
            existingCourse.setName(course.getName());
        }

        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}