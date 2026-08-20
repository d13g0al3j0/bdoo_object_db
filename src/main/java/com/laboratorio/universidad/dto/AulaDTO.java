package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.Aula;

public class AulaDTO {
    private Long id;
    private String codigo;
    private String edificio;
    private int piso;
    private int capacidad;
    private String tipo;

    public AulaDTO() {}
    public AulaDTO(Aula aula) { id = aula.getId(); codigo = aula.getCodigo(); edificio = aula.getEdificio(); piso = aula.getPiso(); capacidad = aula.getCapacidad(); tipo = aula.getTipo().name(); }
    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getEdificio() { return edificio; }
    public int getPiso() { return piso; }
    public int getCapacidad() { return capacidad; }
    public String getTipo() { return tipo; }
}
