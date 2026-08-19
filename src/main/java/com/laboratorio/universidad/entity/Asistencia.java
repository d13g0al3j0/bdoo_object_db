package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private EstadoAsistencia estado;
    private String observacion;

    @ManyToOne
    private Estudiante estudiante;
    @ManyToOne
    private Paralelo paralelo;

    protected Asistencia() {
    }

    public Asistencia(LocalDate fecha, EstadoAsistencia estado, Estudiante estudiante, Paralelo paralelo) {
        this.fecha = fecha;
        this.estado = estado;
        this.estudiante = estudiante;
        this.paralelo = paralelo;
    }

    public Long getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public EstadoAsistencia getEstado() { return estado; }
    public void setEstado(EstadoAsistencia estado) { this.estado = estado; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public Paralelo getParalelo() { return paralelo; }
    public void setParalelo(Paralelo paralelo) { this.paralelo = paralelo; }
}
