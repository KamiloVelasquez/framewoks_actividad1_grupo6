package com.rollerspeed.rollerspeed.service;

import com.rollerspeed.rollerspeed.model.Estudiante;
import com.rollerspeed.rollerspeed.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestión de Estudiantes
 * Implementa principio de Responsabilidad Única
 * Contiene toda la lógica de negocio relacionada con estudiantes
 */
@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    /**
     * Registra un nuevo estudiante
     * Valida que el correo no exista previamente
     */
    public Estudiante registrarEstudiante(Estudiante estudiante) {
        // Validar que el correo no exista
        if (estudianteRepository.existsByCorreo(estudiante.getCorreo())) {
            throw new RuntimeException("Ya existe un estudiante con ese correo electrónico");
        }
        
        return estudianteRepository.save(estudiante);
    }

    /**
     * Lista todos los estudiantes
     */
    public List<Estudiante> listarTodosLosEstudiantes() {
        return estudianteRepository.findAll();
    }

    /**
     * Busca un estudiante por ID
     */
    public Optional<Estudiante> buscarPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    /**
     * Busca un estudiante por correo
     */
    public Optional<Estudiante> buscarPorCorreo(String correo) {
        return estudianteRepository.findByCorreo(correo);
    }

    /**
     * Actualiza los datos de un estudiante
     */
    public Estudiante actualizarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    /**
     * Elimina un estudiante por ID
     */
    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    /**
     * Cuenta el total de estudiantes registrados
     */
    public long contarEstudiantes() {
        return estudianteRepository.countTotalEstudiantes();
    }

    /**
     * Verifica si un estudiante existe por ID
     */
    public boolean existeEstudiante(Long id) {
        return estudianteRepository.existsById(id);
    }
}