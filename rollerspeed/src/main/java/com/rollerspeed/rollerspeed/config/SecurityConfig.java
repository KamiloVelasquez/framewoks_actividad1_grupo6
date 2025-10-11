package com.rollerspeed.rollerspeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
    // CONFIGURACIÓN 1: API REST 
    // Para este escenario usaremos HTTP Basic Authentication
    // ============================================================
    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/api/**") // Solo aplica para las rutas /api/** con esto permite que otras rutas no sean afectadas
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {}) // Habilita HTTP Basic
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sin sesiones
            )
            .csrf(csrf -> csrf.disable()); // Desactiva CSRF para API

        return http.build();
    }

    // ============================================================
    // CONFIGURACIÓN 2: Aplicación Web
    // Aqui dejamos las rutas públicas y privadas de la app web
    // ============================================================
    @Bean
    @Order(2)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/mision", "/vision", "/servicios", "/valores",
                                 "/objetivos", "/contacto", "/Eventos", 
                                 "/horarios/**", "/estudiantes/**", "/registro/**", 
                                 "/css/**", "/js/**", "/images/**",
                                 "/v3/api-docs", "/v3/api-docs/**",
                                 "/swagger-ui.html", "/swagger-ui/**").permitAll()
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
    // USUARIO APIS EN MEMORIA
    // ============================================================
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails apiUser = User.builder()
            .username("api_user") // Usuario para la API
            .password(passwordEncoder().encode("api123")) // Contraseña
            .roles("API") // Rol
            .build();

        UserDetails admin = User.builder()
            .username("admin") // Usuario admin
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