package com.erickwck.modules.candidate.usecases;

import com.erickwck.infrastructure.exceptions.UserNotFoundException;
import com.erickwck.modules.candidate.dto.ProfileCandidateResponseDto;
import com.erickwck.modules.candidate.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public ProfileCandidateResponseDto execute(UUID idCandidate) {

        return candidateRepository.findById(idCandidate)
                .map(ProfileCandidateResponseDto::fromEntityToDto)
                .orElseThrow(() -> new UserNotFoundException());

    }


}
