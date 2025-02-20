package com.erickWck.modules.candidate.controllers;

import com.erickWck.modules.candidate.dto.ProfileCandidateResponseDto;
import com.erickWck.modules.candidate.entity.Candidate;
import com.erickWck.modules.candidate.useCases.CreateCandidateUseCase;
import com.erickWck.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.erickWck.modules.candidate.useCases.ProfileCandidateUseCase;
import com.erickWck.modules.company.entity.Job;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase candidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @PostMapping
    @Operation(summary = "Cadastro candidato",
            description = "Essa função responsável por cadastrar um candidato.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Candidate.class)))
            }),
            @ApiResponse(responseCode = "400",description = "Usuario já cadastrado no sistema")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody Candidate candidate) {

        try {
            var result = candidateUseCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Perfil do candidato",
            description = "Essa função é responsável por buscar informações do perfil do candidato.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ProfileCandidateResponseDto.class)))
            }),
            @ApiResponse(responseCode = "400", description = "User not found")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> getCandidate(HttpServletRequest request) {

        var idCandidate = request.getAttribute("candidateId");
        UUID candidateId = UUID.fromString(String.valueOf(idCandidate));

        try {
            var profile = this.profileCandidateUseCase.execute(candidateId);
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Listagem de vagas disponível para o candidato", description = "Essa função é responsável por listar todas as vagas disponíveis, baseada no filtro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Job.class)))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<Job> findJobByFilter(@RequestParam String filter) {
        return this.listAllJobsByFilterUseCase.execute(filter);
    }

}