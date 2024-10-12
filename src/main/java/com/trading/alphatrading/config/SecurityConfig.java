package com.trading.alphatrading.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/oauth2/**").permitAll() // Allow login and OAuth2 endpoints
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")  // Custom login page
                        .defaultSuccessUrl("/dashboard", true)  // Redirect on successful login
                        .failureUrl("/login?error=true")  // Redirect on failure
                );
        return http.build();
    }
}
