package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.Materia;

public class MateriaDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private int creditos;
    private int horasTeoricas;
    private int horasPracticas;
    private int semestre;

    public MateriaDTO() {}
    public MateriaDTO(Materia materia) {
        id = materia.getId(); codigo = materia.getCodigo(); nombre = materia.getNombre(); descripcion = materia.getDescripcion();
        creditos = materia.getCreditos(); horasTeoricas = materia.getHorasTeoricas(); horasPracticas = materia.getHorasPracticas(); semestre = materia.getSemestre();
    }
    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getCreditos() { return creditos; }
    public int getHorasTeoricas() { return horasTeoricas; }
    public int getHorasPracticas() { return horasPracticas; }
    public int getSemestre() { return semestre; }
}
