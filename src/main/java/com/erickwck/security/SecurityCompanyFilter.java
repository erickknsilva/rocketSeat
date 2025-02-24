package com.erickwck.security;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.erickwck.modules.company.provider.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityCompanyFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String header = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/company")) {

            if (header != null) {
                var token = jwtProvider.validationToken(header);
                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                request.setAttribute("companyID", token.getSubject());
                var roles = token.getClaim("roles").asList(Object.class);

                var simpleGrantedAuthoritiesList = getSimpleGrantedAuthorities(roles);
                authenticationTokenAndContextHolder(token, simpleGrantedAuthoritiesList);
            }
        }

        filterChain.doFilter(request, response);
    }

    private static List<SimpleGrantedAuthority> getSimpleGrantedAuthorities(List<Object> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(
                        "ROLE_" + role.toString().toUpperCase()))
                .toList();
    }

    private static void authenticationTokenAndContextHolder(DecodedJWT token,
                                                            List<SimpleGrantedAuthority> simpleGrantedAuthoritiesList) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(token.getSubject(), null, simpleGrantedAuthoritiesList);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }


}
