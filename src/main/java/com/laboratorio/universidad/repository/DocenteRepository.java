package com.laboratorio.universidad.repository;

import com.laboratorio.universidad.entity.Docente;
import jakarta.persistence.EntityManager;

public class DocenteRepository extends JpaRepository<Docente> {
    public DocenteRepository(EntityManager entityManager) {
        super(entityManager, Docente.class);
    }
}
