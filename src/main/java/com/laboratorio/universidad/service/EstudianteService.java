package com.laboratorio.universidad.service;

import com.laboratorio.universidad.dto.EstudianteRequest;
import com.laboratorio.universidad.entity.Carrera;
import com.laboratorio.universidad.entity.Estudiante;
import com.laboratorio.universidad.entity.PlanEstudios;
import com.laboratorio.universidad.exception.EntidadNoEncontradaException;
import com.laboratorio.universidad.exception.ReglaNegocioException;
import jakarta.persistence.EntityManager;

public class EstudianteService {
    private final EntityManager entityManager;

    public EstudianteService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Estudiante crear(EstudianteRequest request) {
        validar(request);
        return TransactionExecutor.execute(entityManager, () -> {
            Estudiante estudiante = new Estudiante(request.getCi(), request.getNombre(), request.getApellido(),
                    request.getCorreo(), request.getCodigoEstudiante());
            aplicarReferencias(estudiante, request);
            entityManager.persist(estudiante);
            return estudiante;
        });
    }

    public Estudiante actualizar(Long id, EstudianteRequest request) {
        validar(request);
        return TransactionExecutor.execute(entityManager, () -> {
            Estudiante estudiante = buscar(id);
            estudiante.setCi(request.getCi());
            estudiante.setNombre(request.getNombre());
            estudiante.setApellido(request.getApellido());
            estudiante.setCorreo(request.getCorreo());
            estudiante.setTelefono(request.getTelefono());
            estudiante.setCodigoEstudiante(request.getCodigoEstudiante());
            if (request.getSemestreActual() != null) {
                estudiante.setSemestreActual(request.getSemestreActual());
            }
            aplicarReferencias(estudiante, request);
            return estudiante;
        });
    }

    public void eliminar(Long id) {
        TransactionExecutor.execute(entityManager, () -> {
            Estudiante estudiante = buscar(id);
            entityManager.remove(estudiante);
            return null;
        });
    }

    private Estudiante buscar(Long id) {
        Estudiante estudiante = entityManager.find(Estudiante.class, id);
        if (estudiante == null) {
            throw new EntidadNoEncontradaException("Estudiante no encontrado: " + id);
        }
        return estudiante;
    }

    private void aplicarReferencias(Estudiante estudiante, EstudianteRequest request) {
        if (request.getSemestreActual() != null && request.getSemestreActual() < 0) {
            throw new ReglaNegocioException("El semestre actual no puede ser negativo");
        }
        if (request.getCarreraId() != null) {
            estudiante.setCarrera(entityManager.find(Carrera.class, request.getCarreraId()));
            if (estudiante.getCarrera() == null) {
                throw new EntidadNoEncontradaException("Carrera no encontrada: " + request.getCarreraId());
            }
        }
        if (request.getPlanEstudiosId() != null) {
            estudiante.setPlanEstudios(entityManager.find(PlanEstudios.class, request.getPlanEstudiosId()));
            if (estudiante.getPlanEstudios() == null) {
                throw new EntidadNoEncontradaException("Plan de estudios no encontrado: " + request.getPlanEstudiosId());
            }
        }
    }

    private void validar(EstudianteRequest request) {
        if (request == null || isBlank(request.getCi()) || isBlank(request.getNombre())
                || isBlank(request.getApellido()) || isBlank(request.getCorreo())
                || isBlank(request.getCodigoEstudiante())) {
            throw new ReglaNegocioException("ci, nombre, apellido, correo y código son obligatorios");
        }
        if (!request.getCorreo().contains("@")) {
            throw new ReglaNegocioException("El correo no es válido");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
