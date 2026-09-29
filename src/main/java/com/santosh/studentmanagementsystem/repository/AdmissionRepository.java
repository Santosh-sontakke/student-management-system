package com.santosh.studentmanagementsystem.repository;

import com.santosh.studentmanagementsystem.model.Admission;
import com.santosh.studentmanagementsystem.model.AdmissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
    List<Admission> findAllByStatusOrderByRegisteredAtAsc(AdmissionStatus status);
    boolean existsByStudentEmailIgnoreCaseAndCourseId(String email, Long courseId);
}
