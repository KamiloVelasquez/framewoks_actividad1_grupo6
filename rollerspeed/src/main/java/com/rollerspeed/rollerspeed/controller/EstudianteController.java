package com.rollerspeed.rollerspeed.controller;

import com.rollerspeed.rollerspeed.model.Estudiante;
import com.rollerspeed.rollerspeed.service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Estudiantes", description = "Gestión de estudiantes en la academia Rollerspeed")
@RestController
//@RequestMapping("/api/estudiantes")
@RequestMapping(method = RequestMethod.GET, path = "/api/estudiantes")
public class EstudianteController {

    //@Autowired
    private EstudianteService estudianteService;

    @Operation(summary = "Listar todos los estudiantes", 
               description = "Devuelve una lista con todos los estudiantes registrados.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema(implementation = Estudiante.class)))
    @GetMapping
    public ResponseEntity<List<Estudiante>> listarTodos() {
        return ResponseEntity.ok(estudianteService.listarTodosLosEstudiantes());
    }

    @Operation(summary = "Buscar estudiante por ID", 
               description = "Obtiene un estudiante específico por su identificador único.")
    @ApiResponse(responseCode = "200", description = "Estudiante encontrado")
    @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarPorId(
            @Parameter(description = "ID del estudiante", required = true)
            @PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteService.buscarPorId(id);
        return estudiante.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar estudiante por correo", 
               description = "Busca un estudiante usando su dirección de correo electrónico.")
    @ApiResponse(responseCode = "200", description = "Estudiante encontrado")
    @ApiResponse(responseCode = "404", description = "No se encontró estudiante con ese correo")
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Estudiante> buscarPorCorreo(
            @Parameter(description = "Correo del estudiante", required = true)
            @PathVariable String correo) {
        Optional<Estudiante> estudiante = estudianteService.buscarPorCorreo(correo);
        return estudiante.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar un nuevo estudiante", 
               description = "Crea un nuevo estudiante. El correo debe ser único.")
    @ApiResponse(responseCode = "201", description = "Estudiante creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Correo ya existe o datos inválidos")
    @PostMapping
    public ResponseEntity<Estudiante> registrarEstudiante(@RequestBody Estudiante estudiante) {
        try {
            Estudiante nuevo = estudianteService.registrarEstudiante(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(summary = "Actualizar estudiante", 
               description = "Actualiza los datos de un estudiante existente.")
    @ApiResponse(responseCode = "200", description = "Estudiante actualizado")
    @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(
            @Parameter(description = "ID del estudiante a actualizar", required = true)
            @PathVariable Long id,
            @RequestBody Estudiante estudiante) {
        // Aquí puedes validar que el ID coincida con el del objeto
        estudiante.setId(id);
        Estudiante actualizado = estudianteService.actualizarEstudiante(estudiante);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar estudiante", 
               description = "Elimina un estudiante por su ID.")
    @ApiResponse(responseCode = "204", description = "Estudiante eliminado")
    @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(
            @Parameter(description = "ID del estudiante a eliminar", required = true)
            @PathVariable Long id) {
        if (!estudianteService.existeEstudiante(id)) {
            return ResponseEntity.notFound().build();
        }
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Contar total de estudiantes", 
               description = "Devuelve el número total de estudiantes registrados.")
    @ApiResponse(responseCode = "200", description = "Número de estudiantes devuelto")
    @GetMapping("/total")
    public ResponseEntity<Long> contarEstudiantes() {
        long total = estudianteService.contarEstudiantes();
        return ResponseEntity.ok(total);
    }
}