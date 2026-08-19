package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PeriodoAcademico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nombre;
    private int gestion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoPeriodo estado = EstadoPeriodo.PLANIFICADO;

    @OneToMany(mappedBy = "periodo")
    private List<Paralelo> paralelos = new ArrayList<>();

    @OneToMany(mappedBy = "periodo")
    private List<Inscripcion> inscripciones = new ArrayList<>();

    protected PeriodoAcademico() {
    }

    public PeriodoAcademico(String nombre, int gestion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.gestion = gestion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getGestion() { return gestion; }
    public void setGestion(int gestion) { this.gestion = gestion; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public EstadoPeriodo getEstado() { return estado; }
    public void setEstado(EstadoPeriodo estado) { this.estado = estado; }
    public List<Paralelo> getParalelos() { return paralelos; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }
}
