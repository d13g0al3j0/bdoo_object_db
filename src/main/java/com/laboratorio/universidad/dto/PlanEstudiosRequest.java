package com.laboratorio.universidad.dto;

import java.time.LocalDate;

public class PlanEstudiosRequest {
    private String codigo;
    private String version;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long carreraId;

    public PlanEstudiosRequest() {}
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public Long getCarreraId() { return carreraId; }
    public void setCarreraId(Long carreraId) { this.carreraId = carreraId; }
}
