package com.laboratorio.universidad.service;

import com.laboratorio.universidad.dto.DashboardDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class DashboardService {
    private final EntityManager entityManager;

    public DashboardService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public DashboardDTO obtener() {
        long estudiantes = contar("Estudiante");
        long docentes = contar("Docente");
        long carreras = contar("Carrera");
        long materias = contar("Materia");
        long inscripciones = contar("Inscripcion");
        long pagos = contar("Pago");
        Number ingresos = safeNumber("SELECT COALESCE(SUM(p.monto), 0) FROM Pago p WHERE p.estado = 'PAGADO'");
        Number promedio = safeNumber("SELECT COALESCE(AVG(e.promedio), 0) FROM Estudiante e");
        return new DashboardDTO(estudiantes, docentes, carreras, materias, inscripciones, pagos,
                ingresos.doubleValue(), promedio.doubleValue());
    }

    private long contar(String entityName) {
        try {
            return entityManager.createQuery("SELECT COUNT(e) FROM " + entityName + " e", Long.class)
                    .getSingleResult();
        } catch (PersistenceException exception) {
            return 0;
        }
    }

    private Number safeNumber(String jpql) {
        try {
            return entityManager.createQuery(jpql, Number.class).getSingleResult();
        } catch (PersistenceException exception) {
            return 0;
        }
    }
}
