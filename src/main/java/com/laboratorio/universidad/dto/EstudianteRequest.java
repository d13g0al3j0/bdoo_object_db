package com.laboratorio.universidad.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EstudianteRequest {
    @NotBlank
    private String ci;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    @Email
    private String correo;
    private String telefono;
    @NotBlank
    private String codigoEstudiante;
    private Integer semestreActual;
    private Long carreraId;
    private Long planEstudiosId;

    public EstudianteRequest() {
    }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCodigoEstudiante() { return codigoEstudiante; }
    public void setCodigoEstudiante(String codigoEstudiante) { this.codigoEstudiante = codigoEstudiante; }
    public Integer getSemestreActual() { return semestreActual; }
    public void setSemestreActual(Integer semestreActual) { this.semestreActual = semestreActual; }
    public Long getCarreraId() { return carreraId; }
    public void setCarreraId(Long carreraId) { this.carreraId = carreraId; }
    public Long getPlanEstudiosId() { return planEstudiosId; }
    public void setPlanEstudiosId(Long planEstudiosId) { this.planEstudiosId = planEstudiosId; }
}
