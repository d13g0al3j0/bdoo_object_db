package com.laboratorio.universidad.config;

import com.laboratorio.universidad.entity.Estudiante;
import jakarta.persistence.EntityManager;

public final class DemoDataInitializer {
    private DemoDataInitializer() {
    }

    public static void initialize(EntityManager entityManager) {
        Long students = entityManager.createQuery("SELECT COUNT(e) FROM Estudiante e", Long.class).getSingleResult();
        if (students > 0) {
            return;
        }

        entityManager.getTransaction().begin();
        try {
                entityManager.persist(new Estudiante("E-100", "Ana", "Gómez",
                        "ana.gomez@universidad.test", "2026001"));
                entityManager.persist(new Estudiante("E-101", "Bruno", "López",
                        "bruno.lopez@universidad.test", "2026002"));
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw exception;
        }
    }
}
