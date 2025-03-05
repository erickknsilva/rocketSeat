package com.erickwck.modules.candidate.usecases;

import com.erickwck.infrastructure.exceptions.JobNotFoundException;
import com.erickwck.infrastructure.exceptions.CandidateNotFoundException;
import com.erickwck.modules.candidate.entity.ApplyJobEntity;
import com.erickwck.modules.candidate.repository.ApplyJobRepository;
import com.erickwck.modules.candidate.repository.CandidateRepository;
import com.erickwck.modules.company.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ApplyJobCandidateUseCase {

    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {

         candidateRepository.findById(idCandidate)
                .orElseThrow(CandidateNotFoundException::new);

        jobRepository.findById(idJob)
                .orElseThrow(JobNotFoundException::new);

        var applyBuild = ApplyJobEntity.builder()
                .candidateID(idCandidate).jobID(idJob)
                .build();

        return applyJobRepository.save(applyBuild);

    }

}
