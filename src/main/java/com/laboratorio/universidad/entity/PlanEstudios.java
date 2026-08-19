package com.laboratorio.universidad.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PlanEstudios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String codigo;
    private String version;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    @ManyToOne
    private Carrera carrera;

    @OneToMany(mappedBy = "planEstudios", cascade = CascadeType.ALL)
    private List<PlanMateria> materias = new ArrayList<>();

    protected PlanEstudios() {
    }

    public PlanEstudios(String codigo, String version, LocalDate fechaInicio) {
        this.codigo = codigo;
        this.version = version;
        this.fechaInicio = fechaInicio;
        this.estado = "VIGENTE";
    }

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Carrera getCarrera() { return carrera; }
    public void setCarrera(Carrera carrera) { this.carrera = carrera; }
    public List<PlanMateria> getMaterias() { return materias; }

    public void addMateria(PlanMateria planMateria) {
        materias.add(planMateria);
        planMateria.setPlanEstudios(this);
    }
}
