package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.TipoAula;

public class AulaRequest {
    private String codigo;
    private String edificio;
    private int piso;
    private int capacidad;
    private TipoAula tipo;

    public AulaRequest() {}
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getEdificio() { return edificio; }
    public void setEdificio(String edificio) { this.edificio = edificio; }
    public int getPiso() { return piso; }
    public void setPiso(int piso) { this.piso = piso; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public TipoAula getTipo() { return tipo; }
    public void setTipo(TipoAula tipo) { this.tipo = tipo; }
}
