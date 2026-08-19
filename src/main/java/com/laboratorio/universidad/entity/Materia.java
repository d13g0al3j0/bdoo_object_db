package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String codigo;
    @NotBlank
    private String nombre;
    private String descripcion;

    @PositiveOrZero
    private int creditos;
    private int horasTeoricas;
    private int horasPracticas;
    private int semestre;

    @ManyToMany
    private Set<Materia> prerrequisitos = new HashSet<>();

    @OneToMany(mappedBy = "materia")
    private Set<PlanMateria> planes = new HashSet<>();

    protected Materia() {
    }

    public Materia(String codigo, String nombre, int creditos, int semestre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.semestre = semestre;
    }

    public Long getId() { return id; }
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
    public Set<Materia> getPrerrequisitos() { return prerrequisitos; }
    public Set<PlanMateria> getPlanes() { return planes; }

    public void addPrerrequisito(Materia materia) {
        prerrequisitos.add(materia);
    }
}
