package com.erickWck.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder(toBuilder = true)
public record AuthCompanyDto(

        @Schema(example = "tech_solutions", requiredMode = REQUIRED,
                minLength = 5, maxLength = 100, description = "Username criado no cadastro da empresa.")
        String username,

        @Schema(example = "SuperSecurePass2024", requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 10, maxLength = 100, description = "Senha criada no cadastro da empresa.")
        String password
) {
}
