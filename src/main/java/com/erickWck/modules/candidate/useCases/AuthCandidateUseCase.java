package com.erickWck.modules.candidate.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.erickWck.modules.candidate.dto.AuthCandidateDtoRequest;
import com.erickWck.modules.candidate.dto.AuthCandidateDtoResponse;
import com.erickWck.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;


@Service
public class AuthCandidateUseCase {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthCandidateUseCase(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${security.token.candidate}")
    private String secret;


    public AuthCandidateDtoResponse execute(AuthCandidateDtoRequest request) throws AuthenticationException {

        var candidateExist = candidateRepository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/Password incorrect"));

        var passwordEquals = passwordEncoder.matches(request.password(), candidateExist.getPassword());

        if (!passwordEquals) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secret);
        Instant expiresIn = Instant.now().plus(Duration.ofHours(1));

        var token = JWT.create()
                .withIssuer("erickWck")
                .withSubject(candidateExist.getId().toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .sign(algorithm);

        var candidateResponse = AuthCandidateDtoResponse.builder()
                .acess_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();


        return candidateResponse;
    }

}

