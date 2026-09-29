package com.santosh.studentmanagementsystem.dto;

import com.santosh.studentmanagementsystem.model.AdmissionStatus;
import jakarta.validation.constraints.NotNull;

public record AdmissionDecisionRequest(@NotNull AdmissionStatus status, String batch) {}
