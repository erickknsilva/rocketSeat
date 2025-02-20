package com.erickWck.modules.candidate.dto;

import com.erickWck.modules.candidate.entity.Candidate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ProfileCandidateResponseDto(
        UUID id,
        @Schema(example = "Erick Silva")
        String name,
        @Schema(example = "erickWck")
        String username,
        @Schema(example = "erickWck@gmail.com")
        String email,
        @Schema(example = "Desenvolvedor Java")
        String description
) {

    public static ProfileCandidateResponseDto fromEntityToDto(Candidate candidate) {
        return new ProfileCandidateResponseDto(candidate.getId(), candidate.getName(), candidate.getUsername(),
                candidate.getEmail(), candidate.getDescription());
    }

}
