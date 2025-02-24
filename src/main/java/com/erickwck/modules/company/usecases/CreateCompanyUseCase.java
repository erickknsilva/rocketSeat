package com.erickwck.modules.company.usecases;

import com.erickwck.infrastructure.exceptions.UsuarioAlreadExist;
import com.erickwck.modules.company.entity.CompanyEntity;
import com.erickwck.modules.company.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((username) -> {
                    throw new UsuarioAlreadExist();
                });

        var password = passwordEncoder.encode(companyEntity.getPassword());

        companyEntity.setPassword(password);

        return companyRepository.save(companyEntity);
    }


}