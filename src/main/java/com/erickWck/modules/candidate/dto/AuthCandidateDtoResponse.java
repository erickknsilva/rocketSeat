package com.erickWck.modules.candidate.dto;

import lombok.Builder;

@Builder
public record AuthCandidateDtoResponse(
        String acess_token,
        Long expires_in
) {
}
