package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalTime;

@Entity
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    @ManyToOne
    private Paralelo paralelo;
    @ManyToOne
    private Aula aula;

    protected Horario() {
    }

    public Horario(DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaFin, Aula aula) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.aula = aula;
    }

    public Long getId() { return id; }
    public DiaSemana getDiaSemana() { return diaSemana; }
    public void setDiaSemana(DiaSemana diaSemana) { this.diaSemana = diaSemana; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public Paralelo getParalelo() { return paralelo; }
    public void setParalelo(Paralelo paralelo) { this.paralelo = paralelo; }
    public Aula getAula() { return aula; }
    public void setAula(Aula aula) { this.aula = aula; }
}
