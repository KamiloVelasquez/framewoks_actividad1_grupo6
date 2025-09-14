package com.rollerspeed.rollerspeed.controller;

import com.rollerspeed.rollerspeed.model.Estudiante;
import com.rollerspeed.rollerspeed.model.Instructor;
import com.rollerspeed.rollerspeed.model.Clase;
import com.rollerspeed.rollerspeed.service.EstudianteService;
import com.rollerspeed.rollerspeed.service.InstructorService;
import com.rollerspeed.rollerspeed.service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

/**
 * Controlador para manejar los formularios de registro
 * Principio de Responsabilidad Única - solo maneja registro
 */
@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private ClaseService claseService;

    /**
     * Muestra el formulario de inscripción de estudiantes
     */
    @GetMapping("/estudiante")
    public String mostrarFormularioEstudiante(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "registro/formulario-estudiante";
    }

    /**
     * Procesa el formulario de inscripción de estudiantes
     */
    @PostMapping("/estudiante")
    public String procesarRegistroEstudiante(@Valid @ModelAttribute("estudiante") Estudiante estudiante,
                                           BindingResult result,
                                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "registro/formulario-estudiante";
        }

        try {
            estudianteService.registrarEstudiante(estudiante);
            redirectAttributes.addFlashAttribute("mensaje", 
                "¡Estudiante registrado exitosamente! Bienvenido a RollerSpeed.");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/estudiantes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", 
                "Error al registrar estudiante: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/registro/estudiante";
        }
    }

    /**
     * Muestra el formulario de inscripción de instructores
     */
    @GetMapping("/instructor")
    public String mostrarFormularioInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "registro/formulario-instructor";
    }

    /**
     * Procesa el formulario de inscripción de instructores
     */
    @PostMapping("/instructor")
    public String procesarRegistroInstructor(@Valid @ModelAttribute("instructor") Instructor instructor,
                                           BindingResult result,
                                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "registro/formulario-instructor";
        }

        try {
            instructorService.registrarInstructor(instructor);
            redirectAttributes.addFlashAttribute("mensaje", 
                "¡Instructor registrado exitosamente!");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/registro/instructor";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", 
                "Error al registrar instructor: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/registro/instructor";
        }
    }

    /**
     * Muestra el formulario de programación de clases
     */
    @GetMapping("/clases")
    public String mostrarFormularioClase(Model model) {
        model.addAttribute("clase", new Clase());
        model.addAttribute("instructores", instructorService.listarTodosLosInstructores());
        return "registro/formulario-clase";
    }

    /**
     * Procesa el formulario de programación de clases
     */
    @PostMapping("/clases")
    public String procesarRegistroClase(@Valid @ModelAttribute("clase") Clase clase,
                                       BindingResult result,
                                       Model model,
                                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("instructores", instructorService.listarTodosLosInstructores());
            return "registro/formulario-clase";
        }

        try {
            claseService.programarClase(clase);
            redirectAttributes.addFlashAttribute("mensaje", 
                "¡Clase programada exitosamente!");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/registro/clases";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", 
                "Error al programar clase: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/registro/clases";
        }
    }
}