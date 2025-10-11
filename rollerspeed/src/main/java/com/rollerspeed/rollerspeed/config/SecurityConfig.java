package com.rollerspeed.rollerspeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // ============================================================
    // CONFIGURACIÓN 1: API REST (HTTP Basic)
    // ============================================================
    @Bean
    @Order(1) // Se evalúa PRIMERO
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/api/**") // SOLO rutas /api/**
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()) // HTTP Basic habilitado
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .csrf(csrf -> csrf.disable()); // Sin CSRF para APIs

        return http.build();
    }

    // ============================================================
    // CONFIGURACIÓN 2: Aplicación Web (Form Login)
    // ============================================================
    @Bean
    @Order(2) // Se evalúa DESPUÉS
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/**") // TODO lo que NO sea /api/**
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/mision", "/vision", "/servicios", "/valores",
                    "/objetivos", "/contacto", "/Eventos", 
                    "/horarios/**", "/estudiantes/**", "/registro/**", 
                    "/css/**", "/js/**", "/images/**",
                    "/v3/api-docs", "/v3/api-docs/**",
                    "/swagger-ui.html", "/swagger-ui/**",
                    "/login", "/logout"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }

    // ============================================================
    // Usuarios en memoria para HTTP Basic
    // ============================================================
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails apiUser = User.builder()
            .username("api_user")
            .password(passwordEncoder().encode("api123"))
            .roles("API")
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin123"))
            .roles("ADMIN", "API")
            .build();

        return new InMemoryUserDetailsManager(apiUser, admin);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}