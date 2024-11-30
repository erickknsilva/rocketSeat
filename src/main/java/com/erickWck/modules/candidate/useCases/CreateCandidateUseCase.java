package com.erickWck.modules.candidate.useCases;

import com.erickWck.modules.candidate.entity.Candidate;
import com.erickWck.infra.exceptions.UsuarioAlreadExist;
import com.erickWck.modules.candidate.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public Candidate execute(Candidate candidate) {
        candidateRepository.
                findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UsuarioAlreadExist();
                });

        return candidateRepository.save(candidate);
    }

}