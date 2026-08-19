package com.laboratorio.universidad.repository;

import com.laboratorio.universidad.entity.Estudiante;
import jakarta.persistence.EntityManager;

public class EstudianteRepository extends JpaRepository<Estudiante> {
    public EstudianteRepository(EntityManager entityManager) {
        super(entityManager, Estudiante.class);
    }
}
