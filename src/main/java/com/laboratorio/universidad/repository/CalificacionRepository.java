package com.laboratorio.universidad.repository;

import com.laboratorio.universidad.entity.Calificacion;
import jakarta.persistence.EntityManager;

public class CalificacionRepository extends JpaRepository<Calificacion> {
    public CalificacionRepository(EntityManager entityManager) {
        super(entityManager, Calificacion.class);
    }
}
