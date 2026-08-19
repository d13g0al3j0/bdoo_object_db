package com.laboratorio.universidad.repository;

import com.laboratorio.universidad.entity.Carrera;
import jakarta.persistence.EntityManager;

public class CarreraRepository extends JpaRepository<Carrera> {
    public CarreraRepository(EntityManager entityManager) {
        super(entityManager, Carrera.class);
    }
}
