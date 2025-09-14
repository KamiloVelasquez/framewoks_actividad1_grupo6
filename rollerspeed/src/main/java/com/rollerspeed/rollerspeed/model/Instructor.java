package com.rollerspeed.rollerspeed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * Entidad Instructor - Representa a los instructores de la escuela de patinaje
 * Responsabilidad única: gestionar datos de instructores
 */
@Entity
@Table(name = "instructores")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @Column(name = "fecha_nacimiento", nullable = false)
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @Column(name = "genero", nullable = false, length = 10)
    @NotBlank(message = "El género es obligatorio")
    private String genero;

    @Column(name = "correo", nullable = false, length = 150, unique = true)
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener formato válido")
    private String correo;

    @Column(name = "telefono", nullable = false, length = 20)
    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

    @Column(name = "especialidad", nullable = false, length = 100)
    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    @Column(name = "experiencia_anos", nullable = false)
    @NotNull(message = "Los años de experiencia son obligatorios")
    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    private Integer experienciaAnos;

    // Constructores
    public Instructor() {}

    public Instructor(String nombre, LocalDate fechaNacimiento, String genero, 
                     String correo, String telefono, String especialidad, Integer experienciaAnos) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.correo = correo;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.experienciaAnos = experienciaAnos;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public Integer getExperienciaAnos() { return experienciaAnos; }
    public void setExperienciaAnos(Integer experienciaAnos) { this.experienciaAnos = experienciaAnos; }
}