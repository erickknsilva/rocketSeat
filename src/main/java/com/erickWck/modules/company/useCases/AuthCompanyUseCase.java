package com.erickWck.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.erickWck.modules.company.dto.AuthCompanyDto;
import com.erickWck.modules.company.entity.Company;
import com.erickWck.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secret;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String execute(AuthCompanyDto dto) throws AuthenticationException {

        var company = companyRepository.findByUsername(dto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/Password incorrect"));

        passwordIsEquals(dto, company);

        return getToken(company);

    }

    private void passwordIsEquals(AuthCompanyDto dto,
                                  Company company) throws AuthenticationException {

        var passwordMatcher = this.passwordEncoder.matches(dto.password(), company.getPassword());
        if (!passwordMatcher) {
            throw new AuthenticationException();
        }
    }

    private String getToken(Company company) {
        var token = JWT.create()
                .withIssuer("erickWck")
                .withSubject(company.getId().toString())
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .sign(Algorithm.HMAC256(secret));

        return token;
    }

}
