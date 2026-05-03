package com.hirehub.service.job;

import com.hirehub.entity.Job;
import com.hirehub.repository.job.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job, Long employerId) {
        job.setEmployerId(employerId);
        job.setPostedDate(LocalDate.now());
        job.setActive(true);
        return jobRepository.save(job);
    }

    public Job updateJob(Long id, Job updatedJob, Long employerId) {
        Job existing = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        if (!existing.getEmployerId().equals(employerId))
            throw new RuntimeException("Unauthorized");
        existing.setTitle(updatedJob.getTitle());
        existing.setDescription(updatedJob.getDescription());
        existing.setLocation(updatedJob.getLocation());
        existing.setSalaryMin(updatedJob.getSalaryMin());
        existing.setSalaryMax(updatedJob.getSalaryMax());
        existing.setJobType(updatedJob.getJobType());
        existing.setExperienceLevel(updatedJob.getExperienceLevel());
        existing.setExpiryDate(updatedJob.getExpiryDate());
        return jobRepository.save(existing);
    }

    public void deleteJob(Long id, Long employerId) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        if (!job.getEmployerId().equals(employerId))
            throw new RuntimeException("Unauthorized");
        jobRepository.deleteById(id);
    }

    public List<Job> getJobsByEmployer(Long employerId) {
        return jobRepository.findByEmployerId(employerId);
    }

    public List<Job> searchByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) return jobRepository.findAll();
        return jobRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }
}