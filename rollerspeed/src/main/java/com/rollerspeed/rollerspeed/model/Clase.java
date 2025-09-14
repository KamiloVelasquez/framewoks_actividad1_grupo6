package com.rollerspeed.rollerspeed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Entidad Clase - Representa las clases programadas en la escuela
 * Responsabilidad única: gestionar información de clases y horarios
 */
@Entity
@Table(name = "clases")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank(message = "El nombre de la clase es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @Column(name = "nivel", nullable = false, length = 50)
    @NotBlank(message = "El nivel es obligatorio")
    private String nivel;

    @Column(name = "descripcion", length = 500)
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @Column(name = "fecha_hora", nullable = false)
    @NotNull(message = "La fecha y hora son obligatorias")
    @Future(message = "La clase debe programarse en el futuro")
    private LocalDateTime fechaHora;

    @Column(name = "duracion_minutos", nullable = false)
    @NotNull(message = "La duración es obligatoria")
    @Min(value = 30, message = "La duración mínima es 30 minutos")
    @Max(value = 180, message = "La duración máxima es 180 minutos")
    private Integer duracionMinutos;

    @Column(name = "capacidad_maxima", nullable = false)
    @NotNull(message = "La capacidad máxima es obligatoria")
    @Min(value = 1, message = "La capacidad mínima es 1 estudiante")
    @Max(value = 20, message = "La capacidad máxima es 20 estudiantes")
    private Integer capacidadMaxima;

    @Column(name = "instructor_id")
    private Long instructorId; // Simplificamos sin @ManyToOne para el MVP

    // Constructores
    public Clase() {}

    public Clase(String nombre, String nivel, String descripcion, LocalDateTime fechaHora, 
                Integer duracionMinutos, Integer capacidadMaxima, Long instructorId) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.duracionMinutos = duracionMinutos;
        this.capacidadMaxima = capacidadMaxima;
        this.instructorId = instructorId;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Integer getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; }

    public Integer getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(Integer capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }

    public Long getInstructorId() { return instructorId; }
    public void setInstructorId(Long instructorId) { this.instructorId = instructorId; }
}