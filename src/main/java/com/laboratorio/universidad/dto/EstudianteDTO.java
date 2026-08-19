package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.Estudiante;

public class EstudianteDTO {
    private Long id;
    private String codigoEstudiante;
    private String nombreCompleto;
    private String correo;
    private String carrera;
    private String estadoAcademico;

    public EstudianteDTO() {
    }

    public EstudianteDTO(Estudiante estudiante) {
        this.id = estudiante.getId();
        this.codigoEstudiante = estudiante.getCodigoEstudiante();
        this.nombreCompleto = estudiante.getNombre() + " " + estudiante.getApellido();
        this.correo = estudiante.getCorreo();
        this.carrera = estudiante.getCarrera() == null ? null : estudiante.getCarrera().getNombre();
        this.estadoAcademico = estudiante.getEstadoAcademico().name();
    }

    public Long getId() { return id; }
    public String getCodigoEstudiante() { return codigoEstudiante; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getCorreo() { return correo; }
    public String getCarrera() { return carrera; }
    public String getEstadoAcademico() { return estadoAcademico; }
}
