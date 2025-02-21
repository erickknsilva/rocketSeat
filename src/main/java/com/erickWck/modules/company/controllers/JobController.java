package com.erickWck.modules.company.controllers;

import com.erickWck.modules.company.dto.JobDto;
import com.erickWck.modules.company.entity.JobEntity;
import com.erickWck.modules.company.useCases.CreateJobUseCase;
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
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public JobEntity create(@Valid @RequestBody JobDto requestDto, HttpServletRequest request) {

        String companyId = request.getAttribute("companyID").toString();

        /** var jobSave = Job.builder()
         .description(requestDto.description())
         .level(requestDto.level())
         .beneficios(requestDto.beneficios())
         .company_Id(UUID.fromString(companyId))
         .build();
         **/
        var jobSave = JobDto.fromDtotoEntity(requestDto, UUID.fromString(companyId));
        return createJobUseCase.execute(jobSave);
    }

}
