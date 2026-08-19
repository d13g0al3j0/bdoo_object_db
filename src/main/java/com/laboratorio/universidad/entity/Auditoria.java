package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private OperacionAuditoria operacion;

    private String entidad;
    private Long entidadId;
    private String descripcion;

    protected Auditoria() {
    }

    public Auditoria(Usuario usuario, OperacionAuditoria operacion, String entidad, Long entidadId, String descripcion) {
        this.usuario = usuario;
        this.operacion = operacion;
        this.entidad = entidad;
        this.entidadId = entidadId;
        this.descripcion = descripcion;
    }

    public Long getId() { return id; }
    public LocalDateTime getFecha() { return fecha; }
    public Usuario getUsuario() { return usuario; }
    public OperacionAuditoria getOperacion() { return operacion; }
    public String getEntidad() { return entidad; }
    public Long getEntidadId() { return entidadId; }
    public String getDescripcion() { return descripcion; }
}
