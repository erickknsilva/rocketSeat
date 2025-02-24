package com.erickWck.modules.company.useCases;

import com.erickWck.infra.exceptions.CompanyNotFoundException;
import com.erickWck.modules.company.entity.JobEntity;
import com.erickWck.modules.company.repository.CompanyRepository;
import com.erickWck.modules.company.repository.JobRepository;
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
                .orElseThrow(() -> new CompanyNotFoundException());

        return this.jobRepository.save(jobEntity);
    }

}