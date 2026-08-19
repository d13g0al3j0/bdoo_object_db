package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Positive
    private BigDecimal monto;
    private LocalDate fecha = LocalDate.now();
    private String numeroRecibo;

    @Enumerated(EnumType.STRING)
    private EstadoPago estado = EstadoPago.PENDIENTE;

    @ManyToOne
    private Estudiante estudiante;
    @ManyToOne
    private ConceptoPago concepto;

    protected Pago() {
    }

    public Pago(BigDecimal monto, String numeroRecibo, Estudiante estudiante, ConceptoPago concepto) {
        this.monto = monto;
        this.numeroRecibo = numeroRecibo;
        this.estudiante = estudiante;
        this.concepto = concepto;
    }

    public Long getId() { return id; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getNumeroRecibo() { return numeroRecibo; }
    public void setNumeroRecibo(String numeroRecibo) { this.numeroRecibo = numeroRecibo; }
    public EstadoPago getEstado() { return estado; }
    public void setEstado(EstadoPago estado) { this.estado = estado; }
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public ConceptoPago getConcepto() { return concepto; }
    public void setConcepto(ConceptoPago concepto) { this.concepto = concepto; }
}
