package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.Estudiante;

public class EstudianteDTO {
    private Long id;
    private String ci;
    private String nombre;
    private String apellido;
    private String codigoEstudiante;
    private int semestreActual;
    private String nombreCompleto;
    private String correo;
    private String carrera;
    private String estadoAcademico;

    public EstudianteDTO() {
    }

    public EstudianteDTO(Estudiante estudiante) {
        this.id = estudiante.getId();
        this.ci = estudiante.getCi();
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.codigoEstudiante = estudiante.getCodigoEstudiante();
        this.semestreActual = estudiante.getSemestreActual();
        this.nombreCompleto = estudiante.getNombre() + " " + estudiante.getApellido();
        this.correo = estudiante.getCorreo();
        this.carrera = estudiante.getCarrera() == null ? null : estudiante.getCarrera().getNombre();
        this.estadoAcademico = estudiante.getEstadoAcademico().name();
    }

    public Long getId() { return id; }
    public String getCi() { return ci; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCodigoEstudiante() { return codigoEstudiante; }
    public int getSemestreActual() { return semestreActual; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getCorreo() { return correo; }
    public String getCarrera() { return carrera; }
    public String getEstadoAcademico() { return estadoAcademico; }
}
