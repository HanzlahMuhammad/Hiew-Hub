package com.hirehub.repository.job;

import com.hirehub.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEmployerId(Long employerId);
    List<Job> findByTitleContainingIgnoreCase(String keyword);
}