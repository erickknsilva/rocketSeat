package com.erickwck.modules.company.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secret;

    public DecodedJWT validationToken(String token) {

        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {

            //vai conter o ID da companyID
            return JWT.require(algorithm)
                    .build()
                    .verify(token);

        } catch (JWTVerificationException ex) {
            ex.printStackTrace();
            ex.getCause().getMessage();
            return null;
        }
    }

}
