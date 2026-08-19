package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private BigDecimal nota;
    private LocalDate fechaRegistro = LocalDate.now();
    private String observacion;

    @ManyToOne
    private Estudiante estudiante;
    @ManyToOne
    private Paralelo paralelo;
    @ManyToOne
    private Evaluacion evaluacion;
    @ManyToOne
    private Docente docente;

    protected Calificacion() {
    }

    public Calificacion(BigDecimal nota, Estudiante estudiante, Paralelo paralelo, Evaluacion evaluacion, Docente docente) {
        this.nota = nota;
        this.estudiante = estudiante;
        this.paralelo = paralelo;
        this.evaluacion = evaluacion;
        this.docente = docente;
    }

    public Long getId() { return id; }
    public BigDecimal getNota() { return nota; }
    public void setNota(BigDecimal nota) { this.nota = nota; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public Paralelo getParalelo() { return paralelo; }
    public void setParalelo(Paralelo paralelo) { this.paralelo = paralelo; }
    public Evaluacion getEvaluacion() { return evaluacion; }
    public void setEvaluacion(Evaluacion evaluacion) { this.evaluacion = evaluacion; }
    public Docente getDocente() { return docente; }
    public void setDocente(Docente docente) { this.docente = docente; }
}
