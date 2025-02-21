package com.erickWck.modules.company.useCases;

import com.erickWck.modules.company.entity.JobEntity;
import com.erickWck.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity job) {
        return jobRepository.save(job);
    }

}