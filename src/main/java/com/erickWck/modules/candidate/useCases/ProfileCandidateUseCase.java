package com.erickWck.modules.candidate.useCases;

import com.erickWck.modules.candidate.dto.ProfileCandidateResponseDto;
import com.erickWck.modules.candidate.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public ProfileCandidateResponseDto execute(UUID idCandidate) {

        return candidateRepository.findById(idCandidate)
                .map(ProfileCandidateResponseDto::fromEntityToDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }



}
