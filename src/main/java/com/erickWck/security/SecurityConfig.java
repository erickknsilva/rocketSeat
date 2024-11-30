package com.erickWck.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final String[] routes = {"/candidate/**", "/job/**", "/company/**"};


    @Bean
    public SecurityFilterChain securityFilterChainWeb(HttpSecurity http) throws Exception {

        var filChain = http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authorize) -> authorize.requestMatchers(routes).permitAll().anyRequest().authenticated()).build();
        return filChain;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
