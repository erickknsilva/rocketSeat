package com.erickWck.modules.company.controllers;

import com.erickWck.modules.company.dto.AuthCompanyDto;
import com.erickWck.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping(value = "/company")
    public ResponseEntity<Object> authenticate(@RequestBody AuthCompanyDto authCompanyDto) {
        try {
            var result = this.authCompanyUseCase.execute(authCompanyDto);
            return ResponseEntity.ok().body(result);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());

        }
    }

}
