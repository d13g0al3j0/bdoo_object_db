package com.laboratorio.universidad.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class InscripcionRequest {
    @NotNull
    private Long estudianteId;
    @NotNull
    private Long periodoId;
    @NotEmpty
    private List<Long> paralelos;
    private Long usuarioId;

    public InscripcionRequest() {
    }

    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }
    public Long getPeriodoId() { return periodoId; }
    public void setPeriodoId(Long periodoId) { this.periodoId = periodoId; }
    public List<Long> getParalelos() { return paralelos; }
    public void setParalelos(List<Long> paralelos) { this.paralelos = paralelos; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}
