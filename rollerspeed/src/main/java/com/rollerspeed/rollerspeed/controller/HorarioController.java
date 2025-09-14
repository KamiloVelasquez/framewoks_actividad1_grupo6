package com.rollerspeed.rollerspeed.controller;

import com.rollerspeed.rollerspeed.service.ClaseService;
import com.rollerspeed.rollerspeed.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para gestión de horarios y calendario
 * Maneja visualización de horarios de estudiantes e instructores
 */
@Controller
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private ClaseService claseService;

    @Autowired
    private InstructorService instructorService;

    /**
     * Muestra horarios para estudiantes
     */
    @GetMapping("/estudiantes")
    public String horariosEstudiantes(Model model) {
        model.addAttribute("clases", claseService.listarClasesFuturas());
        model.addAttribute("titulo", "Horarios para Estudiantes");
        return "horarios/calendario-estudiantes";
    }

    /**
     * Muestra horarios para instructores
     */
    @GetMapping("/instructores")
    public String horariosInstructores(Model model) {
        model.addAttribute("clases", claseService.listarClasesFuturas());
        model.addAttribute("instructores", instructorService.listarTodosLosInstructores());
        model.addAttribute("titulo", "Horarios para Instructores");
        return "horarios/calendario-instructores";
    }
}