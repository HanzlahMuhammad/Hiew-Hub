package com.hirehub.service.application;

import com.hirehub.dto.ApplicationRequestDTO;
import com.hirehub.dto.ApplicationResponseDTO;
import com.hirehub.entity.Application;
import com.hirehub.entity.Job;
import com.hirehub.repository.application.ApplicationRepository;
import com.hirehub.repository.job.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired private ApplicationRepository applicationRepository;
    @Autowired private JobRepository jobRepository;

    public ApplicationResponseDTO applyForJob(ApplicationRequestDTO dto, Long candidateId) {
        if (applicationRepository.findByCandidateIdAndJobId(candidateId, dto.getJobId()).isPresent())
            throw new RuntimeException("Already applied for this job");

        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application app = new Application();
        app.setCandidateId(candidateId);
        app.setJobId(dto.getJobId());
        app.setEmployerId(job.getEmployerId());
        app.setStatus("PENDING");
        app.setAppliedDate(LocalDateTime.now());
        app.setCoverLetter(dto.getCoverLetter());

        return convertToResponseDTO(applicationRepository.save(app), job.getTitle());
    }

    public List<ApplicationResponseDTO> getApplicationsForEmployer(Long employerId) {
        return applicationRepository.findByEmployerId(employerId).stream()
                .map(app -> {
                    String title = jobRepository.findById(app.getJobId()).map(Job::getTitle).orElse("Unknown");
                    return convertToResponseDTO(app, title);
                }).collect(Collectors.toList());
    }

    public List<ApplicationResponseDTO> getMyApplications(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId).stream()
                .map(app -> {
                    String title = jobRepository.findById(app.getJobId()).map(Job::getTitle).orElse("Unknown");
                    return convertToResponseDTO(app, title);
                }).collect(Collectors.toList());
    }

    public ApplicationResponseDTO updateStatus(Long applicationId, String status, Long employerId) {
        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        if (!app.getEmployerId().equals(employerId))
            throw new RuntimeException("Unauthorized");
        app.setStatus(status.toUpperCase());
        return convertToResponseDTO(applicationRepository.save(app),
                jobRepository.findById(app.getJobId()).map(Job::getTitle).orElse("Unknown"));
    }

    private ApplicationResponseDTO convertToResponseDTO(Application app, String jobTitle) {
        ApplicationResponseDTO dto = new ApplicationResponseDTO();
        dto.setId(app.getId());
        dto.setJobId(app.getJobId());
        dto.setJobTitle(jobTitle);
        dto.setCandidateId(app.getCandidateId());
        dto.setStatus(app.getStatus());
        dto.setAppliedDate(app.getAppliedDate());
        dto.setCoverLetter(app.getCoverLetter());
        return dto;
    }
}