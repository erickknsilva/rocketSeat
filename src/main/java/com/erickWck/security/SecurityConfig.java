package com.erickWck.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    /**
     * private final String[] routes = {
     * "/candidate/**",
     * "/job/**",
     * "/company/**",
     * "/auth/company",
     * "/candidate/auth"
     * };
     **/

    private static final String[] ROUTES = {
            "/candidate",
            "/company",
            "/company/auth",
            "/candidate/auth",
            "/job"
    };

    private static final String[] SWAGGERROUTES = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**"
    };


    @Autowired
    private SecurityCompanyFilter securityFilter;

    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;

    @Bean
    public SecurityFilterChain securityFilterChainWeb(HttpSecurity http) throws Exception {

        var filChain = http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(ROUTES).permitAll()
                        .requestMatchers(SWAGGERROUTES).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);

        return filChain.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
