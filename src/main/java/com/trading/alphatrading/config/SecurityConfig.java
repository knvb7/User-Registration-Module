package com.trading.alphatrading.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.web.SecurityFilterChain;


public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/oauth2/**").permitAll() // Allow login and OAuth2 endpoints
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")  // Custom login page
                        .defaultSuccessUrl("/dashboard", true)  // Redirect to dashboard on successful login
                        .failureUrl("/login?error=true")  // Redirect to login on failure
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(oidcUserService())) // Fetch user info via OidcUserService
                )
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for simplicity (enable in production)
                .sessionManagement(session -> session
                        .maximumSessions(1)  // Limit to 1 session per user
                        .expiredUrl("/login?expired=true")  // Redirect when session expires
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")  // Redirect to login after logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")  // Clean up session cookies
                );
        return http.build();
    }


    // OIDC service for fetching user info (such as email, name)
    @Bean
    public OidcUserService oidcUserService() {
        return new OidcUserService();
    }
}
