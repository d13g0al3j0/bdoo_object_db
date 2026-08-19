package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class DetalleInscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String estado;
    private LocalDate fechaRegistro = LocalDate.now();

    @ManyToOne
    private Inscripcion inscripcion;
    @ManyToOne
    private Paralelo paralelo;

    protected DetalleInscripcion() {
    }

    public DetalleInscripcion(Paralelo paralelo) {
        this.paralelo = paralelo;
        this.estado = "ACTIVO";
    }

    public Long getId() { return id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public Inscripcion getInscripcion() { return inscripcion; }
    public void setInscripcion(Inscripcion inscripcion) { this.inscripcion = inscripcion; }
    public Paralelo getParalelo() { return paralelo; }
    public void setParalelo(Paralelo paralelo) { this.paralelo = paralelo; }
}
