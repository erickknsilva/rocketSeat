package com.erickWck.modules.candidate.useCases;

import com.erickWck.infra.exceptions.JobNotFoundException;
import com.erickWck.infra.exceptions.CandidateNotFoundException;
import com.erickWck.modules.candidate.entity.ApplyJobEntity;
import com.erickWck.modules.candidate.repository.ApplyJobRepository;
import com.erickWck.modules.candidate.repository.CandidateRepository;
import com.erickWck.modules.company.repository.JobRepository;
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

        var candidateId = candidateRepository.findById(idCandidate)
                .orElseThrow(() -> new CandidateNotFoundException());

        var jobId = jobRepository.findById(idJob)
                .orElseThrow(() -> new JobNotFoundException());

        var applyBuild = ApplyJobEntity.builder()
                .candidateID(idCandidate).jobID(idJob)
                .build();

        return applyJobRepository.save(applyBuild);

    }

}
