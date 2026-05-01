package com.hirehub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String company;
    private String location;

    private Long employerId;

    private Double salaryMin;
    private Double salaryMax;

    private String jobType;
    private String experienceLevel;

    private LocalDate postedDate;
    private LocalDate expiryDate;

    private Boolean active;
}