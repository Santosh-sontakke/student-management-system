package com.santosh.studentmanagementsystem.controller;

import com.santosh.studentmanagementsystem.dto.AdmissionDecisionRequest;
import com.santosh.studentmanagementsystem.dto.RegistrationRequest;
import com.santosh.studentmanagementsystem.model.Admission;
import com.santosh.studentmanagementsystem.model.AdmissionStatus;
import com.santosh.studentmanagementsystem.service.AdmissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class AdmissionController {
    private final AdmissionService service;
    public AdmissionController(AdmissionService service) { this.service = service; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Admission register(@Valid @RequestBody RegistrationRequest request) { return service.register(request); }
    @GetMapping public List<Admission> list(@RequestParam(required = false) AdmissionStatus status) { return service.findAll(status); }
    @GetMapping("/{id}") public Admission get(@PathVariable Long id) { return service.findById(id); }
    @PatchMapping("/{id}/decision") public Admission decide(@PathVariable Long id, @Valid @RequestBody AdmissionDecisionRequest request) { return service.decide(id, request); }
}
