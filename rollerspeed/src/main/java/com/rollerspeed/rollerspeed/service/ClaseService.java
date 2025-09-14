package com.rollerspeed.rollerspeed.service;

import com.rollerspeed.rollerspeed.model.Clase;
import com.rollerspeed.rollerspeed.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestión de Clases
 * Maneja lógica de negocio de programación y horarios
 */
@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private InstructorService instructorService;

    /**
     * Programa una nueva clase
     */
    public Clase programarClase(Clase clase) {
        // Validar que el instructor existe si está asignado
        if (clase.getInstructorId() != null) {
            if (!instructorService.buscarPorId(clase.getInstructorId()).isPresent()) {
                throw new RuntimeException("El instructor seleccionado no existe");
            }
        }
        
        return claseRepository.save(clase);
    }

    /**
     * Lista todas las clases
     */
    public List<Clase> listarTodasLasClases() {
        return claseRepository.findAll();
    }

    /**
     * Lista clases futuras (para calendario)
     */
    public List<Clase> listarClasesFuturas() {
        return claseRepository.findClasesFuturas(LocalDateTime.now());
    }

    /**
     * Lista clases por instructor
     */
    public List<Clase> listarClasesPorInstructor(Long instructorId) {
        return claseRepository.findByInstructorId(instructorId);
    }

    /**
     * Lista clases por nivel
     */
    public List<Clase> listarClasesPorNivel(String nivel) {
        return claseRepository.findByNivel(nivel);
    }

    /**
     * Busca una clase por ID
     */
    public Optional<Clase> buscarPorId(Long id) {
        return claseRepository.findById(id);
    }

    /**
     * Busca clases en un rango de fechas
     */
    public List<Clase> buscarClasesEnRango(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return claseRepository.findClasesEnRango(fechaInicio, fechaFin);
    }

    /**
     * Actualiza una clase
     */
    public Clase actualizarClase(Clase clase) {
        return claseRepository.save(clase);
    }

    /**
     * Elimina una clase
     */
    public void eliminarClase(Long id) {
        claseRepository.deleteById(id);
    }

    /**
     * Asigna un instructor a una clase
     */
    public Clase asignarInstructor(Long claseId, Long instructorId) {
        Optional<Clase> claseOpt = buscarPorId(claseId);
        if (!claseOpt.isPresent()) {
            throw new RuntimeException("La clase no existe");
        }
        
        if (!instructorService.buscarPorId(instructorId).isPresent()) {
            throw new RuntimeException("El instructor no existe");
        }
        
        Clase clase = claseOpt.get();
        clase.setInstructorId(instructorId);
        return actualizarClase(clase);
    }
}