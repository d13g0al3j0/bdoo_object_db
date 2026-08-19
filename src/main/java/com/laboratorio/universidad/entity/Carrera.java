package com.laboratorio.universidad.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nombre;
    @NotBlank
    private String codigo;
    private String gradoAcademico;

    @Positive
    private int duracionSemestres;

    private String modalidad;
    private String estado;

    @ManyToOne
    private Facultad facultad;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
    private List<PlanEstudios> planesEstudios = new ArrayList<>();

    protected Carrera() {
    }

    public Carrera(String nombre, String codigo, int duracionSemestres) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.duracionSemestres = duracionSemestres;
        this.estado = "ACTIVA";
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getGradoAcademico() { return gradoAcademico; }
    public void setGradoAcademico(String gradoAcademico) { this.gradoAcademico = gradoAcademico; }
    public int getDuracionSemestres() { return duracionSemestres; }
    public void setDuracionSemestres(int duracionSemestres) { this.duracionSemestres = duracionSemestres; }
    public String getModalidad() { return modalidad; }
    public void setModalidad(String modalidad) { this.modalidad = modalidad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Facultad getFacultad() { return facultad; }
    public void setFacultad(Facultad facultad) { this.facultad = facultad; }
    public List<PlanEstudios> getPlanesEstudios() { return planesEstudios; }

    public void addPlanEstudios(PlanEstudios planEstudios) {
        planesEstudios.add(planEstudios);
        planEstudios.setCarrera(this);
    }
}
