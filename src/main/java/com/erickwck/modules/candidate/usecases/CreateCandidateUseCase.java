package com.erickwck.modules.candidate.usecases;

import com.erickwck.modules.candidate.entity.CandidateEntity;
import com.erickwck.infrastructure.exceptions.UsuarioAlreadExist;
import com.erickwck.modules.candidate.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;

    private final PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidate) {
        candidateRepository.
                findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UsuarioAlreadExist();
                });
        var crypto = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(crypto);
        return candidateRepository.save(candidate);
    }


}