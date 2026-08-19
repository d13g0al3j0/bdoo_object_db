package com.laboratorio.universidad.repository;

import com.laboratorio.universidad.entity.PeriodoAcademico;
import jakarta.persistence.EntityManager;

public class PeriodoRepository extends JpaRepository<PeriodoAcademico> {
    public PeriodoRepository(EntityManager entityManager) {
        super(entityManager, PeriodoAcademico.class);
    }
}
