package com.santosh.studentmanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record StudentRequest(@NotBlank String name, @NotBlank @Email String email, String phone,
                             String address, String dateOfBirth, String gender, String guardianName,
                             String highestQualification, @DecimalMin("0.0") @DecimalMax("100.0") BigDecimal percentage,
                             String category, String religion, String occupation, boolean physicallyChallenged) {}
