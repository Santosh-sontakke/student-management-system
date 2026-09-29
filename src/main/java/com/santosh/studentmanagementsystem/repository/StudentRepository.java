package com.santosh.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.santosh.studentmanagementsystem.model.Student;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<Student> findByEmailIgnoreCase(String email);
}
