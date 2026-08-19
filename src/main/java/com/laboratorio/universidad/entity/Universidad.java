package com.laboratorio.universidad.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Universidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nombre;
    @NotBlank
    private String sigla;
    private String direccion;
    private String telefono;
    @Email
    private String correo;

    @OneToMany(mappedBy = "universidad", cascade = CascadeType.ALL)
    private List<Facultad> facultades = new ArrayList<>();

    protected Universidad() {
    }

    public Universidad(String nombre, String sigla) {
        this.nombre = nombre;
        this.sigla = sigla;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public List<Facultad> getFacultades() { return facultades; }

    public void addFacultad(Facultad facultad) {
        facultades.add(facultad);
        facultad.setUniversidad(this);
    }
}
