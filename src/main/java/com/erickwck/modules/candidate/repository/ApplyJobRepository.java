package com.erickwck.modules.candidate.repository;

import com.erickwck.modules.candidate.entity.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository
        extends JpaRepository<ApplyJobEntity, UUID> {


}
