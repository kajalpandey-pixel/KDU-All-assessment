package org.example.library.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // for simplicity in tests & API
                .authorizeHttpRequests(auth -> auth
                        // Authorization rules from your table
                        .requestMatchers(HttpMethod.POST,  "/books").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.PATCH, "/books/*/catalog").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,   "/books").hasAnyRole("LIBRARIAN", "MEMBER")

                        .requestMatchers(HttpMethod.POST, "/loans/*/borrow").hasRole("MEMBER")
                        .requestMatchers(HttpMethod.POST, "/loans/*/return").hasRole("MEMBER")

                        .requestMatchers(HttpMethod.GET, "/analytics/audit").hasRole("MEMBER")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Basic Auth

        // Expected behavior:
        // not logged in -> 401, logged in but forbidden -> 403 (default Spring Security behavior)
        return http.build();
    }
}
