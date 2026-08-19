package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Paralelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String codigo;
    private int cupoMaximo;

    @PositiveOrZero
    private int cupoDisponible;

    @Enumerated(EnumType.STRING)
    private EstadoParalelo estado = EstadoParalelo.PLANIFICADO;

    @ManyToOne
    private Materia materia;
    @ManyToOne
    private Docente docente;
    @ManyToOne
    private PeriodoAcademico periodo;
    @ManyToOne
    private Aula aula;

    @OneToMany(mappedBy = "paralelo")
    private List<DetalleInscripcion> detallesInscripcion = new ArrayList<>();
    @OneToMany(mappedBy = "paralelo")
    private List<Horario> horarios = new ArrayList<>();
    @OneToMany(mappedBy = "paralelo")
    private List<Evaluacion> evaluaciones = new ArrayList<>();

    protected Paralelo() {
    }

    public Paralelo(String codigo, int cupoMaximo, Materia materia, PeriodoAcademico periodo) {
        this.codigo = codigo;
        this.cupoMaximo = cupoMaximo;
        this.cupoDisponible = cupoMaximo;
        this.materia = materia;
        this.periodo = periodo;
    }

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public int getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(int cupoMaximo) { this.cupoMaximo = cupoMaximo; }
    public int getCupoDisponible() { return cupoDisponible; }
    public void setCupoDisponible(int cupoDisponible) { this.cupoDisponible = cupoDisponible; }
    public EstadoParalelo getEstado() { return estado; }
    public void setEstado(EstadoParalelo estado) { this.estado = estado; }
    public Materia getMateria() { return materia; }
    public void setMateria(Materia materia) { this.materia = materia; }
    public Docente getDocente() { return docente; }
    public void setDocente(Docente docente) { this.docente = docente; }
    public PeriodoAcademico getPeriodo() { return periodo; }
    public void setPeriodo(PeriodoAcademico periodo) { this.periodo = periodo; }
    public Aula getAula() { return aula; }
    public void setAula(Aula aula) { this.aula = aula; }
    public List<DetalleInscripcion> getDetallesInscripcion() { return detallesInscripcion; }
    public List<Horario> getHorarios() { return horarios; }
    public List<Evaluacion> getEvaluaciones() { return evaluaciones; }
}
