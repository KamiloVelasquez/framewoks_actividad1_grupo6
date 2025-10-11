package com.rollerspeed.rollerspeed.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("apiUserDetailsService")
public class ApiUserDetailsService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Usuarios hardcodeados para la API
        if ("api_user".equals(username)) {
            return User.builder()
                .username("api_user")
                .password(passwordEncoder.encode("api123"))
                .roles("API")
                .build();
        } else if ("admin".equals(username)) {
            return User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN", "API")
                .build();
        }
        
        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}