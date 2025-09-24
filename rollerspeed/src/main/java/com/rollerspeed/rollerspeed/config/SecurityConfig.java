package com.rollerspeed.rollerspeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                
                .requestMatchers("/", "/mision", "/vision", "/servicios", "/valores",
                                 "/objetivos", "/contacto", "/Eventos", 
                                 "/horarios/**", "/estudiantes/**", "/registro/**", 
                                 "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true) 
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")           // URL para cerrar sesión (POST)
                .logoutSuccessUrl("/")          // Redirige a la página principal
                .invalidateHttpSession(true)    // Invalida la sesión
                .deleteCookies("JSESSIONID")    // Elimina la cookie de sesión
                .permitAll()
            );

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
