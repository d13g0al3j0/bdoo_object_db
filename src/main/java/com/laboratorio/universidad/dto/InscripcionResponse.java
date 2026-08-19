package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.Inscripcion;

public class InscripcionResponse {
    private boolean success;
    private String message;
    private Long inscripcionId;
    private String estudiante;
    private int materias;
    private String transaction;

    public InscripcionResponse() {
    }

    public InscripcionResponse(Inscripcion inscripcion) {
        this.success = true;
        this.message = "Inscripción registrada correctamente";
        this.inscripcionId = inscripcion.getId();
        this.estudiante = inscripcion.getEstudiante().getNombre() + " " + inscripcion.getEstudiante().getApellido();
        this.materias = inscripcion.getDetalles().size();
        this.transaction = "COMMITTED";
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Long getInscripcionId() { return inscripcionId; }
    public String getEstudiante() { return estudiante; }
    public int getMaterias() { return materias; }
    public String getTransaction() { return transaction; }
}
