package com.erickWck.modules.company.repository;

import com.erickWck.modules.company.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository
        extends JpaRepository<Job, UUID> {

    List<Job> findByDescriptionContainingIgnoreCase(String filter);

}