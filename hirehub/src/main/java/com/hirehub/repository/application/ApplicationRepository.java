package com.hirehub.repository.application;

import com.hirehub.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByCandidateIdAndJobId(Long candidateId, Long jobId);
    List<Application> findByJobId(Long jobId);
    List<Application> findByEmployerId(Long employerId);
    List<Application> findByCandidateId(Long candidateId);
}