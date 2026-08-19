package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Docente extends Persona {
    private String codigoDocente;
    private String especialidad;
    private String gradoAcademico;
    private LocalDate fechaContratacion;

    protected Docente() {
    }

    public Docente(String ci, String nombre, String apellido, String correo, String codigoDocente) {
        super(ci, nombre, apellido, correo);
        this.codigoDocente = codigoDocente;
        this.fechaContratacion = LocalDate.now();
    }

    public String getCodigoDocente() { return codigoDocente; }
    public void setCodigoDocente(String codigoDocente) { this.codigoDocente = codigoDocente; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getGradoAcademico() { return gradoAcademico; }
    public void setGradoAcademico(String gradoAcademico) { this.gradoAcademico = gradoAcademico; }
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
}
