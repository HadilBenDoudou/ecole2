package com.ecole.ecole.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
                .csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/signup", "/api/auth/login", "/api/auth/forgot-password").permitAll() // Allow access
                        .requestMatchers(HttpMethod.DELETE, "/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/auth/**").permitAll()
                        .requestMatchers("/api/classes", "/error").permitAll()
                        .requestMatchers("/api/classes/**", "/error").permitAll()
                        .requestMatchers("/api/subjects/**").permitAll()
                                .requestMatchers("/api/subjects").permitAll() // Allow unauthenticated access to login
                        .requestMatchers("/api/enseignants").permitAll()
                        .requestMatchers("/api/enseignants/**").permitAll()

                        .requestMatchers("/api/eleves").permitAll() // Allow unauthenticated access to login
                        .requestMatchers("/api/eleves/**").permitAll()
                        .requestMatchers("/api/auth/login").permitAll() // Allow unauthenticated access to login
                        .requestMatchers("/api/auth/update-profile").permitAll() // Allow unauthenticated access to login
                        .requestMatchers("/api/auth/update-profile/**").permitAll() // Allow unauthenticated access to login

                        .requestMatchers(HttpMethod.POST, "/api/etudiants/import").permitAll()// Allow PUT for all users
                        .requestMatchers("/api/auth/**").authenticated() // Require authentication for auth routes
                        .requestMatchers("/api/etudiants/importer").authenticated() // Require authentication for import route
                        .anyRequest().authenticated() // Protect all other routes
                )
                .httpBasic(httpSecurity -> { }); // Use basic authentication

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Set your frontend URL
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow these HTTP methods
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Allow these headers
        configuration.setAllowCredentials(true); // Allow credentials

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply the configuration to all endpoints
        return source;
    }
}
