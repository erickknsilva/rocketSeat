package com.erickwck.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public record AuthCandidateDtoRequest(
        @Schema(example = "johndoe",
                description = "Insira o username que foi cadastrado.",
                minLength = 5, maxLength = 30, requiredMode = RequiredMode.REQUIRED)
        String username,
        @Schema(example = "12345678910",
                description = "Insira a senha que foi cadastrado.",
                minLength = 8, maxLength = 50, requiredMode = RequiredMode.REQUIRED)
        String password
) {
}
