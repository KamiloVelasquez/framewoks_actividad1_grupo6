// src/main/java/com/rollerspeed/rollerspeed/controller/EstudianteWebController.java
package com.rollerspeed.rollerspeed.controller;

import com.rollerspeed.rollerspeed.model.Estudiante;
import com.rollerspeed.rollerspeed.service.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EstudianteWebController {

    private final EstudianteService estudianteService;

    public EstudianteWebController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/estudiantes")
    public String listarEstudiantes(Model model) {
        List<Estudiante> estudiantes = estudianteService.listarTodosLosEstudiantes();
        long totalEstudiantes = estudianteService.contarEstudiantes();
        
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("totalEstudiantes", totalEstudiantes);
        return "estudiantes/listar-estudiantes";
    }
}