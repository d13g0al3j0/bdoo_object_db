package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.EstadoPeriodo;
import java.time.LocalDate;

public class PeriodoRequest {
    private String nombre;
    private int gestion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoPeriodo estado;

    public PeriodoRequest() {}
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
}
