package com.laboratorio.universidad.dto;

public class MateriaRequest {
    private String codigo;
    private String nombre;
    private String descripcion;
    private int creditos;
    private int horasTeoricas;
    private int horasPracticas;
    private int semestre;

    public MateriaRequest() {}
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }
    public int getHorasTeoricas() { return horasTeoricas; }
    public void setHorasTeoricas(int horasTeoricas) { this.horasTeoricas = horasTeoricas; }
    public int getHorasPracticas() { return horasPracticas; }
    public void setHorasPracticas(int horasPracticas) { this.horasPracticas = horasPracticas; }
    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
}
