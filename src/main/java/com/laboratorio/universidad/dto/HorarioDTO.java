package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.Horario;

public class HorarioDTO {
    private Long id;
    private String diaSemana;
    private String horaInicio;
    private String horaFin;
    private Long paraleloId;
    private Long aulaId;

    public HorarioDTO() {}
    public HorarioDTO(Horario horario) {
        id = horario.getId(); diaSemana = horario.getDiaSemana().name(); horaInicio = horario.getHoraInicio().toString(); horaFin = horario.getHoraFin().toString();
        paraleloId = horario.getParalelo() == null ? null : horario.getParalelo().getId(); aulaId = horario.getAula() == null ? null : horario.getAula().getId();
    }
    public Long getId() { return id; }
    public String getDiaSemana() { return diaSemana; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFin() { return horaFin; }
    public Long getParaleloId() { return paraleloId; }
    public Long getAulaId() { return aulaId; }
}
