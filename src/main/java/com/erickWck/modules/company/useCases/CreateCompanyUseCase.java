package com.erickWck.modules.company.useCases;

import com.erickWck.infra.exceptions.UsuarioAlreadExist;
import com.erickWck.modules.company.entity.Company;
import com.erickWck.modules.company.repository.CompanyRepository;
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

    public Company execute(Company company) {

        companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent((username) -> {
                    throw new UsuarioAlreadExist();
                });

        var password = passwordEncoder.encode(company.getPassword());

        company.setPassword(password);

        return companyRepository.save(company);
    }


}