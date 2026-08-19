package com.laboratorio.universidad.service;

import com.laboratorio.universidad.entity.Auditoria;
import com.laboratorio.universidad.entity.DetalleInscripcion;
import com.laboratorio.universidad.entity.EstadoAcademico;
import com.laboratorio.universidad.entity.EstadoInscripcion;
import com.laboratorio.universidad.entity.EstadoPeriodo;
import com.laboratorio.universidad.entity.Estudiante;
import com.laboratorio.universidad.entity.Inscripcion;
import com.laboratorio.universidad.entity.OperacionAuditoria;
import com.laboratorio.universidad.entity.Paralelo;
import com.laboratorio.universidad.entity.PeriodoAcademico;
import com.laboratorio.universidad.entity.Usuario;
import com.laboratorio.universidad.exception.CupoInsuficienteException;
import com.laboratorio.universidad.exception.EntidadNoEncontradaException;
import com.laboratorio.universidad.exception.InscripcionDuplicadaException;
import com.laboratorio.universidad.exception.PeriodoNoActivoException;
import com.laboratorio.universidad.exception.PrerrequisitoNoCumplidoException;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InscripcionService {
    private static final double NOTA_APROBACION = 51.0;

    private final EntityManager entityManager;

    public InscripcionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Inscripcion registrar(Long estudianteId, Long periodoId, List<Long> paraleloIds, Usuario usuario) {
        return TransactionExecutor.execute(entityManager, () -> {
            Estudiante estudiante = require(Estudiante.class, estudianteId, "Estudiante no encontrado: " + estudianteId);
            PeriodoAcademico periodo = require(PeriodoAcademico.class, periodoId, "Periodo no encontrado: " + periodoId);

            if (estudiante.getEstadoAcademico() != EstadoAcademico.ACTIVO) {
                throw new IllegalStateException("El estudiante no está activo");
            }
            if (periodo.getEstado() != EstadoPeriodo.ACTIVO) {
                throw new PeriodoNoActivoException("El periodo académico no está activo");
            }

            Long duplicadas = entityManager.createQuery(
                            "SELECT COUNT(i) FROM Inscripcion i WHERE i.estudiante.id = :estudianteId "
                                    + "AND i.periodo.id = :periodoId AND i.estado <> :anulada", Long.class)
                    .setParameter("estudianteId", estudianteId)
                    .setParameter("periodoId", periodoId)
                    .setParameter("anulada", EstadoInscripcion.ANULADA)
                    .getSingleResult();
            if (duplicadas > 0) {
                throw new InscripcionDuplicadaException("El estudiante ya tiene una inscripción en el periodo");
            }

            Set<Long> unicos = new HashSet<>(paraleloIds);
            if (unicos.size() != paraleloIds.size() || paraleloIds.isEmpty()) {
                throw new InscripcionDuplicadaException("La solicitud contiene paralelos repetidos o vacía");
            }

            Inscripcion inscripcion = new Inscripcion(estudiante, periodo);
            for (Long paraleloId : paraleloIds) {
                Paralelo paralelo = require(Paralelo.class, paraleloId, "Paralelo no encontrado: " + paraleloId);
                validarParalelo(estudiante, paralelo, periodo);
                paralelo.setCupoDisponible(paralelo.getCupoDisponible() - 1);
                inscripcion.addDetalle(new DetalleInscripcion(paralelo));
            }

            inscripcion.setEstado(EstadoInscripcion.CONFIRMADA);
            entityManager.persist(inscripcion);
            entityManager.flush();
            entityManager.persist(new Auditoria(usuario, OperacionAuditoria.INSCRIBIR,
                    "Inscripcion", inscripcion.getId(), "Inscripción registrada"));
            return inscripcion;
        });
    }

    private void validarParalelo(Estudiante estudiante, Paralelo paralelo, PeriodoAcademico periodo) {
        if (paralelo.getPeriodo() != periodo) {
            throw new IllegalStateException("El paralelo no pertenece al periodo seleccionado");
        }
        if (paralelo.getCupoDisponible() <= 0) {
            throw new CupoInsuficienteException("El paralelo " + paralelo.getCodigo() + " no tiene cupos disponibles");
        }
        for (var prerrequisito : paralelo.getMateria().getPrerrequisitos()) {
            Long aprobadas = entityManager.createQuery(
                            "SELECT COUNT(c) FROM Calificacion c WHERE c.estudiante.id = :estudianteId "
                                    + "AND c.paralelo.materia = :materia AND c.nota >= :minimo", Long.class)
                    .setParameter("estudianteId", estudiante.getId())
                    .setParameter("materia", prerrequisito)
                    .setParameter("minimo", java.math.BigDecimal.valueOf(NOTA_APROBACION))
                    .getSingleResult();
            if (aprobadas == 0) {
                throw new PrerrequisitoNoCumplidoException("No cumple el prerrequisito " + prerrequisito.getNombre());
            }
        }
    }

    private <T> T require(Class<T> type, Long id, String message) {
        T entity = entityManager.find(type, id);
        if (entity == null) {
            throw new EntidadNoEncontradaException(message);
        }
        return entity;
    }
}
