package com.rollerspeed.rollerspeed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * Entidad Estudiante - Representa a los estudiantes de la escuela de patinaje
 * Implementa los principios SOLID con responsabilidad única
 */
@Entity
@Table(name = "estudiantes")
public class Estudiante {

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

    @Column(name = "medio_pago", nullable = false, length = 50)
    @NotBlank(message = "El medio de pago es obligatorio")
    private String medioPago;

    // Constructores
    public Estudiante() {}

    public Estudiante(String nombre, LocalDate fechaNacimiento, String genero, 
                     String correo, String telefono, String medioPago) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.correo = correo;
        this.telefono = telefono;
        this.medioPago = medioPago;
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

    public String getMedioPago() { return medioPago; }
    public void setMedioPago(String medioPago) { this.medioPago = medioPago; }
}