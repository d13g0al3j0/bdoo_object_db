package com.laboratorio.universidad.repository;

import com.laboratorio.universidad.entity.Paralelo;
import jakarta.persistence.EntityManager;

public class ParaleloRepository extends JpaRepository<Paralelo> {
    public ParaleloRepository(EntityManager entityManager) {
        super(entityManager, Paralelo.class);
    }
}
