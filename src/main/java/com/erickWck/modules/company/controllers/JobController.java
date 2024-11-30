package com.erickWck.modules.company.controllers;

import com.erickWck.modules.company.entity.Job;
import com.erickWck.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/job")
@RestController
public class JobController {

    @Autowired
    private CreateJobUseCase jobUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job create(@Valid @RequestBody Job job) {
        return jobUseCase.execute(job);
    }

}
