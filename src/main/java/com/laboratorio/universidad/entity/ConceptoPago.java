package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ConceptoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoConceptoPago tipo;

    @OneToMany(mappedBy = "concepto")
    private List<Pago> pagos = new ArrayList<>();

    protected ConceptoPago() {
    }

    public ConceptoPago(String nombre, TipoConceptoPago tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public TipoConceptoPago getTipo() { return tipo; }
    public void setTipo(TipoConceptoPago tipo) { this.tipo = tipo; }
    public List<Pago> getPagos() { return pagos; }
}
