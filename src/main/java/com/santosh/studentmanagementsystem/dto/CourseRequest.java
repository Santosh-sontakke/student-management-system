package com.santosh.studentmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;

        public record CourseRequest(@NotBlank String name, @NotBlank String code, String description, Boolean active) {}
