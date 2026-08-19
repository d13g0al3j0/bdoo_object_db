package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String codigo;
    private String edificio;
    private int piso;

    @Positive
    private int capacidad;

    @Enumerated(EnumType.STRING)
    private TipoAula tipo;

    protected Aula() {
    }

    public Aula(String codigo, String edificio, int piso, int capacidad, TipoAula tipo) {
        this.codigo = codigo;
        this.edificio = edificio;
        this.piso = piso;
        this.capacidad = capacidad;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
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
