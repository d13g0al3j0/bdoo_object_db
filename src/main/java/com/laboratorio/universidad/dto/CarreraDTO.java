package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.Carrera;

public class CarreraDTO {
    private Long id;
    private String nombre;
    private String codigo;
    private String gradoAcademico;
    private int duracionSemestres;
    private String modalidad;
    private String estado;

    public CarreraDTO() {}
    public CarreraDTO(Carrera carrera) {
        id = carrera.getId(); nombre = carrera.getNombre(); codigo = carrera.getCodigo();
        gradoAcademico = carrera.getGradoAcademico(); duracionSemestres = carrera.getDuracionSemestres();
        modalidad = carrera.getModalidad(); estado = carrera.getEstado();
    }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
    public String getGradoAcademico() { return gradoAcademico; }
    public int getDuracionSemestres() { return duracionSemestres; }
    public String getModalidad() { return modalidad; }
    public String getEstado() { return estado; }
}
