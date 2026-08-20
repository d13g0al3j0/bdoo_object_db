package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.DiaSemana;
import java.time.LocalTime;

public class HorarioRequest {
    private DiaSemana diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long paraleloId;
    private Long aulaId;

    public HorarioRequest() {}
    public DiaSemana getDiaSemana() { return diaSemana; }
    public void setDiaSemana(DiaSemana diaSemana) { this.diaSemana = diaSemana; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public Long getParaleloId() { return paraleloId; }
    public void setParaleloId(Long paraleloId) { this.paraleloId = paraleloId; }
    public Long getAulaId() { return aulaId; }
    public void setAulaId(Long aulaId) { this.aulaId = aulaId; }
}
