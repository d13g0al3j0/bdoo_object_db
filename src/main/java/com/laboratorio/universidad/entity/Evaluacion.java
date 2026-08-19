package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoEvaluacion tipo;
    private LocalDate fecha;
    private BigDecimal porcentaje;

    @ManyToOne
    private Paralelo paralelo;

    @OneToMany(mappedBy = "evaluacion")
    private List<Calificacion> calificaciones = new ArrayList<>();

    protected Evaluacion() {
    }

    public Evaluacion(TipoEvaluacion tipo, LocalDate fecha, BigDecimal porcentaje, Paralelo paralelo) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.porcentaje = porcentaje;
        this.paralelo = paralelo;
    }

    public Long getId() { return id; }
    public TipoEvaluacion getTipo() { return tipo; }
    public void setTipo(TipoEvaluacion tipo) { this.tipo = tipo; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }
    public Paralelo getParalelo() { return paralelo; }
    public void setParalelo(Paralelo paralelo) { this.paralelo = paralelo; }
    public List<Calificacion> getCalificaciones() { return calificaciones; }
}
