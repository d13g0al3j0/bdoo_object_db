package com.laboratorio.universidad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class PlanMateria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int semestre;

    @PositiveOrZero
    private int creditos;

    private boolean obligatoria;
    private boolean electiva;

    @ManyToOne
    private PlanEstudios planEstudios;

    @ManyToOne
    private Materia materia;

    protected PlanMateria() {
    }

    public PlanMateria(Materia materia, int semestre, int creditos, boolean obligatoria, boolean electiva) {
        this.materia = materia;
        this.semestre = semestre;
        this.creditos = creditos;
        this.obligatoria = obligatoria;
        this.electiva = electiva;
    }

    public Long getId() { return id; }
    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }
    public boolean isObligatoria() { return obligatoria; }
    public void setObligatoria(boolean obligatoria) { this.obligatoria = obligatoria; }
    public boolean isElectiva() { return electiva; }
    public void setElectiva(boolean electiva) { this.electiva = electiva; }
    public PlanEstudios getPlanEstudios() { return planEstudios; }
    public void setPlanEstudios(PlanEstudios planEstudios) { this.planEstudios = planEstudios; }
    public Materia getMateria() { return materia; }
    public void setMateria(Materia materia) { this.materia = materia; }
}
