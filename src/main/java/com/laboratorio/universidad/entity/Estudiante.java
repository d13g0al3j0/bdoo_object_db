package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Estudiante extends Persona {
    @NotBlank
    private String codigoEstudiante;
    private LocalDate fechaIngreso;
    private int semestreActual;

    @PositiveOrZero
    private BigDecimal promedio = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private EstadoAcademico estadoAcademico = EstadoAcademico.ACTIVO;

    @ManyToOne
    private Carrera carrera;

    @ManyToOne
    private PlanEstudios planEstudios;

    protected Estudiante() {
    }

    public Estudiante(String ci, String nombre, String apellido, String correo, String codigoEstudiante) {
        super(ci, nombre, apellido, correo);
        this.codigoEstudiante = codigoEstudiante;
        this.fechaIngreso = LocalDate.now();
    }

    public String getCodigoEstudiante() { return codigoEstudiante; }
    public void setCodigoEstudiante(String codigoEstudiante) { this.codigoEstudiante = codigoEstudiante; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public int getSemestreActual() { return semestreActual; }
    public void setSemestreActual(int semestreActual) { this.semestreActual = semestreActual; }
    public BigDecimal getPromedio() { return promedio; }
    public void setPromedio(BigDecimal promedio) { this.promedio = promedio; }
    public EstadoAcademico getEstadoAcademico() { return estadoAcademico; }
    public void setEstadoAcademico(EstadoAcademico estadoAcademico) { this.estadoAcademico = estadoAcademico; }
    public Carrera getCarrera() { return carrera; }
    public void setCarrera(Carrera carrera) { this.carrera = carrera; }
    public PlanEstudios getPlanEstudios() { return planEstudios; }
    public void setPlanEstudios(PlanEstudios planEstudios) { this.planEstudios = planEstudios; }
}
