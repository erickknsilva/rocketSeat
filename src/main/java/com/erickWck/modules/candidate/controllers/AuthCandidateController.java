package com.erickWck.modules.candidate.controllers;

import com.erickWck.modules.candidate.dto.AuthCandidateDtoRequest;
import com.erickWck.modules.candidate.dto.AuthCandidateDtoResponse;
import com.erickWck.modules.candidate.useCases.AuthCandidateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Autenticação do candidato.",
            description = "Essa função é responsavel pela autenticacão do usuário e retorna um token Bearer JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = AuthCandidateDtoResponse.class)))
            }),
            @ApiResponse(responseCode = "401", description = "Username/Password incorrect")
    })
    public ResponseEntity<Object> auth(@Valid @RequestBody AuthCandidateDtoRequest request) {

        try {
            var token = this.authCandidateUseCase.execute(request);
            return ResponseEntity.ok().body(token);

        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }

}
