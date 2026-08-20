package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.PeriodoAcademico;

public class PeriodoDTO {
    private Long id;
    private String nombre;
    private int gestion;
    private String fechaInicio;
    private String fechaFin;
    private String estado;

    public PeriodoDTO() {}
    public PeriodoDTO(PeriodoAcademico periodo) {
        id = periodo.getId(); nombre = periodo.getNombre(); gestion = periodo.getGestion(); estado = periodo.getEstado().name();
        fechaInicio = periodo.getFechaInicio() == null ? null : periodo.getFechaInicio().toString();
        fechaFin = periodo.getFechaFin() == null ? null : periodo.getFechaFin().toString();
    }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public int getGestion() { return gestion; }
    public String getFechaInicio() { return fechaInicio; }
    public String getFechaFin() { return fechaFin; }
    public String getEstado() { return estado; }
}
