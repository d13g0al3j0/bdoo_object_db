package com.laboratorio.universidad.repository;

import com.laboratorio.universidad.entity.Inscripcion;
import jakarta.persistence.EntityManager;

public class InscripcionRepository extends JpaRepository<Inscripcion> {
    public InscripcionRepository(EntityManager entityManager) {
        super(entityManager, Inscripcion.class);
    }
}
