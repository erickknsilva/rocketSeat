package com.erickWck.modules.candidate.useCases;

import com.erickWck.modules.company.entity.JobEntity;
import com.erickWck.modules.company.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListAllJobsByFilterUseCase {

    private final JobRepository jobRepository;

    public List<JobEntity> execute(String description) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(description);
    }

}
