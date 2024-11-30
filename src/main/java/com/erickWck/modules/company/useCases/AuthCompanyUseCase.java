package com.erickWck.modules.company.useCases;

import com.erickWck.modules.company.dto.AuthCompanyDto;
import com.erickWck.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AuthCompanyDto dto) throws AuthenticationException {

        var company = companyRepository.findByUsername(dto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Company não encontrado"));

        var passwordMatcher = this.passwordEncoder.matches(dto.password(), company.getPassword());

        if (!passwordMatcher) {
            throw new AuthenticationException();
        }
    }

}
