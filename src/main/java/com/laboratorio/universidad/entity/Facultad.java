package com.laboratorio.universidad.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Facultad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nombre;
    @NotBlank
    private String codigo;
    private String descripcion;

    @ManyToOne
    private Universidad universidad;

    @OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL)
    private List<Carrera> carreras = new ArrayList<>();

    protected Facultad() {
    }

    public Facultad(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Universidad getUniversidad() { return universidad; }
    public void setUniversidad(Universidad universidad) { this.universidad = universidad; }
    public List<Carrera> getCarreras() { return carreras; }

    public void addCarrera(Carrera carrera) {
        carreras.add(carrera);
        carrera.setFacultad(this);
    }
}
