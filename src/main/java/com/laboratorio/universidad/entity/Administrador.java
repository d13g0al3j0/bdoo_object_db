package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends Persona {
    private String cargo;
    private String area;

    protected Administrador() {
    }

    public Administrador(String ci, String nombre, String apellido, String correo, String cargo, String area) {
        super(ci, nombre, apellido, correo);
        this.cargo = cargo;
        this.area = area;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
}
