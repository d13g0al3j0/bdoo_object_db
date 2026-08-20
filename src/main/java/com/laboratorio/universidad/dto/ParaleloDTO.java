package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.Paralelo;

public class ParaleloDTO {
    private Long id;
    private String codigo;
    private int cupoMaximo;
    private int cupoDisponible;
    private String estado;
    private Long materiaId;
    private Long docenteId;
    private Long periodoId;
    private Long aulaId;

    public ParaleloDTO() {}
    public ParaleloDTO(Paralelo paralelo) {
        id = paralelo.getId(); codigo = paralelo.getCodigo(); cupoMaximo = paralelo.getCupoMaximo(); cupoDisponible = paralelo.getCupoDisponible();
        estado = paralelo.getEstado().name(); materiaId = paralelo.getMateria() == null ? null : paralelo.getMateria().getId();
        docenteId = paralelo.getDocente() == null ? null : paralelo.getDocente().getId(); periodoId = paralelo.getPeriodo() == null ? null : paralelo.getPeriodo().getId();
        aulaId = paralelo.getAula() == null ? null : paralelo.getAula().getId();
    }
    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public int getCupoMaximo() { return cupoMaximo; }
    public int getCupoDisponible() { return cupoDisponible; }
    public String getEstado() { return estado; }
    public Long getMateriaId() { return materiaId; }
    public Long getDocenteId() { return docenteId; }
    public Long getPeriodoId() { return periodoId; }
    public Long getAulaId() { return aulaId; }
}
