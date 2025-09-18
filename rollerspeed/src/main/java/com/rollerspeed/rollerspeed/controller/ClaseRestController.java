package com.rollerspeed.rollerspeed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rollerspeed.rollerspeed.model.Clase;
import com.rollerspeed.rollerspeed.service.ClaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Clases", description = "Operaciones para gestionar las clases de patinaje")
@RestController
@RequestMapping("/api/clases")
public class ClaseRestController {

    @Autowired
    private ClaseService claseService;

    @Operation(summary = "Listar todas las clases")
    @GetMapping
    public List<Clase> listarClases() {
        return claseService.listarTodasLasClases();
    }

    @Operation(summary = "Obtener una clase por su ID")
    @GetMapping("/{id}")
    public Clase obtenerClase(@PathVariable Long id) {
        return claseService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
    }

    @Operation(summary = "Crear una nueva clase")
    @PostMapping
    public Clase crearClase(@RequestBody Clase clase) {
        return claseService.programarClase(clase);
    }

    @Operation(summary = "Actualizar una clase existente")
    @PutMapping("/{id}")
    public Clase actualizarClase(@PathVariable Long id, @RequestBody Clase clase) {
        clase.setId(id);
        return claseService.actualizarClase(clase);
    }

    @Operation(summary = "Eliminar una clase")
    @DeleteMapping("/{id}")
    public void eliminarClase(@PathVariable Long id) {
        claseService.eliminarClase(id);
    }
}