package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String ci;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    @Email
    private String correo;

    private String telefono;
    private LocalDate fechaNacimiento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoPersona estado = EstadoPersona.ACTIVO;

    protected Persona() {
    }

    protected Persona(String ci, String nombre, String apellido, String correo) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public Long getId() { return id; }
    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public EstadoPersona getEstado() { return estado; }
    public void setEstado(EstadoPersona estado) { this.estado = estado; }
}
