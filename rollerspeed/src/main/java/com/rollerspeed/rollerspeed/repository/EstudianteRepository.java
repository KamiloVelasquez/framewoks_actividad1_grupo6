package com.rollerspeed.rollerspeed.repository;

import com.rollerspeed.rollerspeed.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio para la entidad Estudiante
 * Extiende JpaRepository para operaciones CRUD básicas
 * Principio de Segregación de Interfaces - solo métodos necesarios
 */
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    
    /**
     * Busca un estudiante por correo electrónico
     * @param correo el correo del estudiante
     * @return Optional con el estudiante si existe
     */
    Optional<Estudiante> findByCorreo(String correo);
    
    /**
     * Verifica si existe un estudiante con el correo dado
     * @param correo el correo a verificar
     * @return true si existe, false si no
     */
    boolean existsByCorreo(String correo);
    
    /**
     * Cuenta el total de estudiantes registrados
     * @return número total de estudiantes
     */
    @Query("SELECT COUNT(e) FROM Estudiante e")
    long countTotalEstudiantes();
}