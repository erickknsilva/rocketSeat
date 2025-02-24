package com.erickwck.modules.company.controllers;

import com.erickwck.modules.company.dto.AuthCompanyDto;
import com.erickwck.modules.company.dto.AuthCompanyDtoResponse;
import com.erickwck.modules.company.usecases.AuthCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@Tag(name = "Company", description = "Informações da empresa.")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping(value = "/auth")
    @Operation(summary = "Autenticação da empresa",
            description = "Essa função é responsavel pela autenticação da empresa e retorna um token Bearer JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema
                            (schema = @Schema(implementation = AuthCompanyDtoResponse.class)))
            }),
            @ApiResponse(responseCode = "401", description = "Username/Password incorrect")
    })
    public ResponseEntity<Object> authenticate(@RequestBody AuthCompanyDto authCompanyDto) {
        try {
            var result = this.authCompanyUseCase.execute(authCompanyDto);
            return ResponseEntity.ok().body(result);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());

        }
    }

}
