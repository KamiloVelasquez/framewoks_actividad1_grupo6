package com.rollerspeed.rollerspeed.repository;

import com.rollerspeed.rollerspeed.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Instructor
 * Maneja operaciones de acceso a datos de instructores
 */
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    
    /**
     * Busca un instructor por correo electrónico
     */
    Optional<Instructor> findByCorreo(String correo);
    
    /**
     * Verifica si existe un instructor con el correo dado
     */
    boolean existsByCorreo(String correo);
    
    /**
     * Busca instructores por especialidad
     */
    List<Instructor> findByEspecialidad(String especialidad);
    
    /**
     * Busca instructores con experiencia mínima
     */
    @Query("SELECT i FROM Instructor i WHERE i.experienciaAnos >= :anosMinimos")
    List<Instructor> findByExperienciaMinima(Integer anosMinimos);
}