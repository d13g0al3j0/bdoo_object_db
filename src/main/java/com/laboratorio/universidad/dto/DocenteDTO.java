package com.laboratorio.universidad.dto;

import com.laboratorio.universidad.entity.Docente;

public class DocenteDTO {
    private Long id;
    private String ci;
    private String nombre;
    private String apellido;
    private String correo;
    private String codigoDocente;
    private String especialidad;
    private String gradoAcademico;

    public DocenteDTO() {}
    public DocenteDTO(Docente docente) {
        id = docente.getId(); ci = docente.getCi(); nombre = docente.getNombre(); apellido = docente.getApellido();
        correo = docente.getCorreo(); codigoDocente = docente.getCodigoDocente(); especialidad = docente.getEspecialidad();
        gradoAcademico = docente.getGradoAcademico();
    }
    public Long getId() { return id; }
    public String getCi() { return ci; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public String getCodigoDocente() { return codigoDocente; }
    public String getEspecialidad() { return especialidad; }
    public String getGradoAcademico() { return gradoAcademico; }
}
