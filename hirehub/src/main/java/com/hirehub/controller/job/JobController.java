package com.hirehub.controller.job;

import com.hirehub.entity.Job;
import com.hirehub.service.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job,
                                         @RequestHeader("X-Employer-Id") Long employerId) {
        return ResponseEntity.ok(jobService.createJob(job, employerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id,
                                         @RequestBody Job job,
                                         @RequestHeader("X-Employer-Id") Long employerId) {
        return ResponseEntity.ok(jobService.updateJob(id, job, employerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id,
                                          @RequestHeader("X-Employer-Id") Long employerId) {
        jobService.deleteJob(id, employerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employer")
    public ResponseEntity<List<Job>> getMyJobs(@RequestHeader("X-Employer-Id") Long employerId) {
        return ResponseEntity.ok(jobService.getJobsByEmployer(employerId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(jobService.searchByKeyword(keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }
}