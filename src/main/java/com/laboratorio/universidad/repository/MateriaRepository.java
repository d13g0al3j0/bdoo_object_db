package com.laboratorio.universidad.repository;

import com.laboratorio.universidad.entity.Materia;
import jakarta.persistence.EntityManager;

public class MateriaRepository extends JpaRepository<Materia> {
    public MateriaRepository(EntityManager entityManager) {
        super(entityManager, Materia.class);
    }
}
