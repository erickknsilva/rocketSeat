package com.erickWck.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.erickWck.modules.company.dto.AuthCompanyDto;
import com.erickWck.modules.company.dto.AuthCompanyDtoResponse;
import com.erickWck.modules.company.entity.CompanyEntity;
import com.erickWck.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secret;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public AuthCompanyDtoResponse execute(AuthCompanyDto dto) throws AuthenticationException {

        var company = companyRepository.findByUsername(dto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/Password incorrect"));

        passwordIsEquals(dto, company);

        return getToken(company);

    }

    private void passwordIsEquals(AuthCompanyDto dto,
                                  CompanyEntity companyEntity) throws AuthenticationException {

        var passwordMatcher = this.passwordEncoder.matches(dto.password(), companyEntity.getPassword());
        if (!passwordMatcher) {
            throw new AuthenticationException();
        }

    }

    private AuthCompanyDtoResponse getToken(CompanyEntity companyEntity) {

        Instant expireIn = Instant.now().plus(Duration.ofHours(2));
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var token = JWT.create()
                .withIssuer("erickWck")
                .withSubject(companyEntity.getId().toString())
                .withExpiresAt(expireIn)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var companyDtoResponse = AuthCompanyDtoResponse.builder()
                .acess_token(token)
                .expires_in(expireIn.toEpochMilli())
                .build();

        return companyDtoResponse;
    }

}
