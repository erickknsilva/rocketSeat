package com.erickWck.modules.candidate.entity;


import com.erickWck.modules.company.entity.JobEntity;
import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.UUID;

@Builder(toBuilder = true)
@Entity(name = "aplly_jobs")
public record ApplyJobEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        UUID id,

        @ManyToOne
        @JoinColumn(name = "candidate_id")
        CandidateEntity candidateEntity,

        @ManyToOne
        @JoinColumn(name = "job_id", insertable = false, updatable = false)
        JobEntity jobEntity,

        @Column(name = "candidate_id", insertable = false, updatable = false)
        UUID candidateID,

        @Column(name = "job_id")
        UUID jobID,

        @CreationTimestamp
        LocalDate createdAt
) {


}
