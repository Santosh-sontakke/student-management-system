package com.santosh.studentmanagementsystem.repository;

import com.santosh.studentmanagementsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
