package com.rollerspeed.rollerspeed.repository;

import com.rollerspeed.rollerspeed.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para la entidad Clase
 * Gestiona consultas relacionadas con horarios y programación
 */
@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {
    
    /**
     * Busca clases por instructor
     */
    List<Clase> findByInstructorId(Long instructorId);
    
    /**
     * Busca clases por nivel
     */
    List<Clase> findByNivel(String nivel);
    
    /**
     * Busca clases futuras (para calendario)
     */
    @Query("SELECT c FROM Clase c WHERE c.fechaHora >= :fechaActual ORDER BY c.fechaHora ASC")
    List<Clase> findClasesFuturas(LocalDateTime fechaActual);
    
    /**
     * Busca clases en un rango de fechas
     */
    @Query("SELECT c FROM Clase c WHERE c.fechaHora BETWEEN :fechaInicio AND :fechaFin ORDER BY c.fechaHora ASC")
    List<Clase> findClasesEnRango(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}