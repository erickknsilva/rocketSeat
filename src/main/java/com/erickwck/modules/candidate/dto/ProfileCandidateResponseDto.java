package com.erickwck.modules.candidate.dto;

import com.erickwck.modules.candidate.entity.CandidateEntity;
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

    public static ProfileCandidateResponseDto fromEntityToDto(CandidateEntity candidate) {
        return new ProfileCandidateResponseDto(candidate.getId(), candidate.getName(), candidate.getUsername(),
                candidate.getEmail(), candidate.getDescription());
    }

}
