package com.trading.alphatrading.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/oauth2/**") // Allow login and OAuth2 endpoints
                        .permitAll()
                        .anyRequest().permitAll()  // All other requests require authentication
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureHandler((request, response, exception) -> {
                            logger.error("OAuth2 Login Failure", exception);
                            response.sendRedirect("/login?error=true");  // Redirect to login with error
                        })
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(oidcUserService())  // OIDC user service to fetch user info
                        )
                )
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for simplicity (re-enable in production)
//                .sessionManagement(session -> session
//                        .maximumSessions(1)  // Limit to 1 session per user
//                        .expiredUrl("/login?expired=true")  // Redirect when session expires
//                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout=true")  // Redirect to login after logout
                        .invalidateHttpSession(true)  // Invalidate session
                        .deleteCookies("JSESSIONID")  // Clean up session cookies
                );
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(registry -> {
//            registry.requestMatchers("/").permitAll();
//            registry.anyRequest().authenticated();
//        })
//                .oauth2Login(oauth2 -> oauth2.loginPage()Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
//                .build();
//    }

    @Bean
    public OidcUserService oidcUserService() {
        return new OidcUserService();  // Handles fetching user info
    }
}