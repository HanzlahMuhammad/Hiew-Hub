package com.hirehub.controller.application;

import com.hirehub.dto.ApplicationRequestDTO;
import com.hirehub.dto.ApplicationResponseDTO;
import com.hirehub.service.application.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<ApplicationResponseDTO> apply(@RequestBody ApplicationRequestDTO dto,
                                                        @RequestHeader("X-Candidate-Id") Long candidateId) {
        return ResponseEntity.ok(applicationService.applyForJob(dto, candidateId));
    }

    @GetMapping("/employer")
    public ResponseEntity<List<ApplicationResponseDTO>> getEmployerApplications(@RequestHeader("X-Employer-Id") Long employerId) {
        return ResponseEntity.ok(applicationService.getApplicationsForEmployer(employerId));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponseDTO>> getMyApplications(@RequestHeader("X-Candidate-Id") Long candidateId) {
        return ResponseEntity.ok(applicationService.getMyApplications(candidateId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationResponseDTO> updateStatus(@PathVariable Long id,
                                                               @RequestParam String status,
                                                               @RequestHeader("X-Employer-Id") Long employerId) {
        return ResponseEntity.ok(applicationService.updateStatus(id, status, employerId));
    }
}