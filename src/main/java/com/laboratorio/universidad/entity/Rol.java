package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RolSistema nombre;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios = new HashSet<>();

    protected Rol() {
    }

    public Rol(RolSistema nombre) {
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public RolSistema getNombre() { return nombre; }
    public void setNombre(RolSistema nombre) { this.nombre = nombre; }
    public Set<Usuario> getUsuarios() { return usuarios; }
}
