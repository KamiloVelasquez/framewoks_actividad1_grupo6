package com.rollerspeed.rollerspeed.controller;

import com.rollerspeed.rollerspeed.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para gestión de estudiantes
 * Maneja listados y consultas de estudiantes
 */
@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    /**
     * Lista todos los estudiantes registrados
     */
    @GetMapping
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.listarTodosLosEstudiantes());
        model.addAttribute("totalEstudiantes", estudianteService.contarEstudiantes());
        return "estudiantes/listar-estudiantes";
    }
}