package com.laboratorio.universidad.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate fecha = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private EstadoInscripcion estado = EstadoInscripcion.PENDIENTE;

    private String observaciones;

    @ManyToOne
    private Estudiante estudiante;
    @ManyToOne
    private PeriodoAcademico periodo;

    @OneToMany(mappedBy = "inscripcion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleInscripcion> detalles = new ArrayList<>();

    protected Inscripcion() {
    }

    public Inscripcion(Estudiante estudiante, PeriodoAcademico periodo) {
        this.estudiante = estudiante;
        this.periodo = periodo;
    }

    public Long getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public EstadoInscripcion getEstado() { return estado; }
    public void setEstado(EstadoInscripcion estado) { this.estado = estado; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public PeriodoAcademico getPeriodo() { return periodo; }
    public void setPeriodo(PeriodoAcademico periodo) { this.periodo = periodo; }
    public List<DetalleInscripcion> getDetalles() { return detalles; }

    public void addDetalle(DetalleInscripcion detalle) {
        detalles.add(detalle);
        detalle.setInscripcion(this);
    }
}
