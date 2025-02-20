package com.erickWck.modules.company.dto;

import com.erickWck.modules.company.entity.Job;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record JobDto(

        @NotBlank(message = "Escreva a descrição da vaga.")
        @Schema(example = "Vaga para pessoa desenvolvedora junior", requiredMode = RequiredMode.REQUIRED)
        String description,

        @NotBlank(message = "Insira os beneficios.F")
        @Schema(example = "Gympass, plano de saúde, Vale alimentação", requiredMode = RequiredMode.REQUIRED)
        String beneficios,

        @NotBlank(message = "Insira o nivel da vaga")
        @Schema(example = "Nivel da vaga (junior,senior, pleno)", requiredMode = RequiredMode.REQUIRED)
        String level

) {
    public static Job fromDtotoEntity(JobDto jobDto, UUID companyId) {
        return new Job(jobDto.level(), jobDto.beneficios(), jobDto.description(), companyId);
    }
}
