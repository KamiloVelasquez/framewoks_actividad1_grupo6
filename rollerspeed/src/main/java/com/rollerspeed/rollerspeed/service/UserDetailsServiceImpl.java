package com.rollerspeed.rollerspeed.service;


import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rollerspeed.rollerspeed.model.Usuario;
import com.rollerspeed.rollerspeed.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }

    @PostMapping("/registro/formulario-usuario")
    public String processFormularioUsuario(@RequestParam String username,
                                           @RequestParam String email,
                                           @RequestParam String password) {
        System.out.println("Registrando usuario...");
        if (usuarioRepository.existsByUsername(username)) {
            return "redirect:/registro/formulario-usuario?error=usuario";
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuarioRepository.save(usuario);

        return "redirect:/login?success";
    }
}
