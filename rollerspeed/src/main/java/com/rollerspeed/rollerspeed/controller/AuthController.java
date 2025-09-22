package com.rollerspeed.rollerspeed.controller;

import com.rollerspeed.rollerspeed.model.Usuario;
import com.rollerspeed.rollerspeed.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registro")
    public String showRegistroForm() {
        return "registro";
    }

    @GetMapping("/registro/formulario-usuario")
    public String showFormularioUsuario() {
        return "registro/formulario-usuario";
    }

    @PostMapping("/registro/formulario-usuario")
    public String processFormularioUsuario(@RequestParam String username,
                                           @RequestParam String email,
                                           @RequestParam String password) {
        if (usuarioRepository.existsByUsername(username)) {
            return "redirect:/registro/formulario-usuario?error=usuario";
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRole("USER");
        usuarioRepository.save(usuario);

        return "redirect:/login?success";
    }

    
}
