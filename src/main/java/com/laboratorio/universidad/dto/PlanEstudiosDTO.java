package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.PlanEstudios;

public class PlanEstudiosDTO {
    private Long id;
    private String codigo;
    private String version;
    private String fechaInicio;
    private String fechaFin;
    private String estado;
    private Long carreraId;

    public PlanEstudiosDTO() {}
    public PlanEstudiosDTO(PlanEstudios plan) {
        id = plan.getId(); codigo = plan.getCodigo(); version = plan.getVersion(); estado = plan.getEstado();
        fechaInicio = plan.getFechaInicio() == null ? null : plan.getFechaInicio().toString();
        fechaFin = plan.getFechaFin() == null ? null : plan.getFechaFin().toString();
        carreraId = plan.getCarrera() == null ? null : plan.getCarrera().getId();
    }
    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getVersion() { return version; }
    public String getFechaInicio() { return fechaInicio; }
    public String getFechaFin() { return fechaFin; }
    public String getEstado() { return estado; }
    public Long getCarreraId() { return carreraId; }
}
