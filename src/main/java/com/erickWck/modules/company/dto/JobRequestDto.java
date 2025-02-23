package com.erickWck.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobRequestDto {

    @NotBlank(message = "Escreva a descrição da vaga.")
    @Schema(example = "Vaga para pessoa desenvolvedora junior", requiredMode = RequiredMode.REQUIRED)
    private String description;

    @NotBlank(message = "Insira os beneficios.")
    @Schema(example = "Gympass, plano de saúde, Vale alimentação", requiredMode = RequiredMode.REQUIRED)
    private String beneficios;

    @NotBlank(message = "Insira o nivel da vaga")
    @Schema(example = "Nivel da vaga (junior,senior, pleno)", requiredMode = RequiredMode.REQUIRED)
    private String level;

    // Construtores, getters e setters são gerados automaticamente pelo @Builder

}
