package com.erickwck.modules.company.usecases;

import com.erickwck.infrastructure.exceptions.CompanyNotFoundException;
import com.erickwck.modules.company.entity.JobEntity;
import com.erickwck.modules.company.repository.CompanyRepository;
import com.erickwck.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {

        companyRepository.findById(jobEntity.getCompany_Id())
                .orElseThrow(CompanyNotFoundException::new);

        return this.jobRepository.save(jobEntity);
    }

}