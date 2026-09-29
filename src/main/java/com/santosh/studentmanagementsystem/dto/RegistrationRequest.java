package com.santosh.studentmanagementsystem.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RegistrationRequest(@NotNull @Valid StudentRequest student, @NotNull Long courseId) {}
