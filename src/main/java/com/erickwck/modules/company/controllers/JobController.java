package com.erickwck.modules.company.controllers;

import com.erickwck.modules.company.dto.JobRequestDto;
import com.erickwck.modules.company.entity.JobEntity;
import com.erickwck.modules.company.usecases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/company")
@RestController
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/job")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "Informações das vagas")
    @Operation(summary = "Cadastro de vagas", description = "Essa função é responsável por cadastras as vagas dentro " +
            "da empresa.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = JobEntity.class))
            }),
            @ApiResponse(responseCode = "404", description = "Company not found")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody JobRequestDto jobDto, HttpServletRequest request) {

        String companyId = request.getAttribute("companyID").toString();

        try {
            var jobSave = JobEntity.builder()
                    .beneficios(jobDto.getBeneficios()).level(jobDto.getLevel())
                    .description(jobDto.getDescription()).company_Id(UUID.fromString(companyId))
                    .build();

            var result = this.createJobUseCase.execute(jobSave);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

}
