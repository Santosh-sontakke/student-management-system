package com.santosh.studentmanagementsystem.service;

import com.santosh.studentmanagementsystem.dto.*;
import com.santosh.studentmanagementsystem.model.*;
import com.santosh.studentmanagementsystem.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AdmissionService {
    private final AdmissionRepository admissions;
    private final StudentRepository students;
    private final CourseRepository courses;
    public AdmissionService(AdmissionRepository admissions, StudentRepository students, CourseRepository courses) {
        this.admissions = admissions; this.students = students; this.courses = courses;
    }
    public List<Admission> findAll(AdmissionStatus status) {
        return status == null ? admissions.findAll() : admissions.findAllByStatusOrderByRegisteredAtAsc(status);
    }
    public Admission findById(Long id) { return admissions.findById(id).orElseThrow(() -> new NotFoundException("Registration " + id + " was not found")); }
    @Transactional
    public Admission register(RegistrationRequest request) {
        StudentRequest details = request.student();
        if (details == null) throw new IllegalArgumentException("student is required");
        Course course = courses.findById(request.courseId()).orElseThrow(() -> new NotFoundException("Course " + request.courseId() + " was not found"));
        if (!course.isActive()) throw new ConflictException("This course is not accepting registrations");
        if (admissions.existsByStudentEmailIgnoreCaseAndCourseId(details.email(), course.getId())) throw new ConflictException("This student is already registered for this course");
        Student student = students.findByEmailIgnoreCase(details.email()).orElseGet(Student::new);
        student.setName(details.name().trim()); student.setEmail(details.email().trim().toLowerCase());
        student.setPhone(details.phone()); student.setAddress(details.address()); student.setDateOfBirth(details.dateOfBirth());
        student.setGender(details.gender()); student.setGuardianName(details.guardianName());
        student.setHighestQualification(details.highestQualification()); student.setPercentage(details.percentage());
        student.setCategory(details.category()); student.setReligion(details.religion()); student.setOccupation(details.occupation());
        student.setPhysicallyChallenged(details.physicallyChallenged()); student = students.save(student);
        Admission admission = new Admission(); admission.setStudent(student); admission.setCourse(course);
        admission.setRegistrationNumber("REG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return admissions.save(admission);
    }
    public Admission decide(Long id, AdmissionDecisionRequest request) {
        Admission admission = findById(id); admission.setStatus(request.status());
        admission.setBatch(request.status() == AdmissionStatus.ADMITTED ? request.batch() : null);
        if (request.status() == AdmissionStatus.ADMITTED && (request.batch() == null || request.batch().isBlank()))
            throw new IllegalArgumentException("batch is required when admitting a student");
        return admissions.save(admission);
    }
}
