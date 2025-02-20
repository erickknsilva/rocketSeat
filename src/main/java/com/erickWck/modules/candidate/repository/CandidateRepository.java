package com.erickWck.modules.candidate.repository;

import com.erickWck.modules.candidate.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository
        extends JpaRepository<Candidate, UUID> {

    Optional<Candidate> findByUsernameOrEmail(String username, String email);
    Optional<Candidate> findByUsername(String username);
}