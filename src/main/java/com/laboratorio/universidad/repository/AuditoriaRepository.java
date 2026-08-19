package com.laboratorio.universidad.repository;

import com.laboratorio.universidad.entity.Auditoria;
import jakarta.persistence.EntityManager;

public class AuditoriaRepository extends JpaRepository<Auditoria> {
    public AuditoriaRepository(EntityManager entityManager) {
        super(entityManager, Auditoria.class);
    }
}
