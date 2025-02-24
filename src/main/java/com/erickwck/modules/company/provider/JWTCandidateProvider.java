package com.erickwck.modules.company.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTCandidateProvider {

    @Value("${security.token.candidate}")
    private String secretkey;

    public DecodedJWT validationToken(String token) {

        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secretkey);

        try {

            var tokenDecoded = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return tokenDecoded; //vai conter o ID do Candidate

        } catch (JWTVerificationException ex) {
            ex.printStackTrace();
            ex.getCause().getMessage();
            return null;
        }
    }

}
