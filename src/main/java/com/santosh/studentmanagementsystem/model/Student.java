package com.santosh.studentmanagementsystem.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "students", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String name;
    @Column(nullable = false, length = 254)
    private String email;
    @Column(length = 30)
    private String phone;
    @Column(length = 1000)
    private String address;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(length = 30)
    private String gender;
    @Column(name = "guardian_name", length = 120)
    private String guardianName;
    @Column(name = "highest_qualification", length = 120)
    private String highestQualification;
    @Column(precision = 5, scale = 2)
    private BigDecimal percentage;
    @Column(length = 60)
    private String category;
    @Column(length = 60)
    private String religion;
    @Column(length = 100)
    private String occupation;
    @Column(name = "physically_challenged", nullable = false)
    private boolean physicallyChallenged;

    public Student() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getGuardianName() { return guardianName; }
    public void setGuardianName(String guardianName) { this.guardianName = guardianName; }
    public String getHighestQualification() { return highestQualification; }
    public void setHighestQualification(String highestQualification) { this.highestQualification = highestQualification; }
    public BigDecimal getPercentage() { return percentage; }
    public void setPercentage(BigDecimal percentage) { this.percentage = percentage; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getReligion() { return religion; }
    public void setReligion(String religion) { this.religion = religion; }
    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }
    public boolean isPhysicallyChallenged() { return physicallyChallenged; }
    public void setPhysicallyChallenged(boolean physicallyChallenged) { this.physicallyChallenged = physicallyChallenged; }
}
