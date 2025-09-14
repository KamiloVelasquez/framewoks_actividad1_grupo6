package com.rollerspeed.rollerspeed.service;

import com.rollerspeed.rollerspeed.model.Instructor;
import com.rollerspeed.rollerspeed.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestión de Instructores
 * Maneja lógica de negocio de instructores
 */
@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    /**
     * Registra un nuevo instructor
     */
    public Instructor registrarInstructor(Instructor instructor) {
        // Validar que el correo no exista
        if (instructorRepository.existsByCorreo(instructor.getCorreo())) {
            throw new RuntimeException("Ya existe un instructor con ese correo electrónico");
        }
        
        return instructorRepository.save(instructor);
    }

    /**
     * Lista todos los instructores
     */
    public List<Instructor> listarTodosLosInstructores() {
        return instructorRepository.findAll();
    }

    /**
     * Busca un instructor por ID
     */
    public Optional<Instructor> buscarPorId(Long id) {
        return instructorRepository.findById(id);
    }

    /**
     * Busca instructores por especialidad
     */
    public List<Instructor> buscarPorEspecialidad(String especialidad) {
        return instructorRepository.findByEspecialidad(especialidad);
    }

    /**
     * Busca instructores con experiencia mínima
     */
    public List<Instructor> buscarPorExperienciaMinima(Integer anosMinimos) {
        return instructorRepository.findByExperienciaMinima(anosMinimos);
    }

    /**
     * Actualiza los datos de un instructor
     */
    public Instructor actualizarInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    /**
     * Elimina un instructor
     */
    public void eliminarInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}