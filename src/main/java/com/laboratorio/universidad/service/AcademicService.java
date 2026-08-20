package com.laboratorio.universidad.service;

import com.laboratorio.universidad.dto.CarreraRequest;
import com.laboratorio.universidad.dto.DocenteRequest;
import com.laboratorio.universidad.dto.MateriaRequest;
import com.laboratorio.universidad.dto.PlanEstudiosRequest;
import com.laboratorio.universidad.dto.PeriodoRequest;
import com.laboratorio.universidad.dto.AulaRequest;
import com.laboratorio.universidad.dto.ParaleloRequest;
import com.laboratorio.universidad.dto.HorarioRequest;
import com.laboratorio.universidad.entity.Aula;
import com.laboratorio.universidad.entity.Carrera;
import com.laboratorio.universidad.entity.Docente;
import com.laboratorio.universidad.entity.Materia;
import com.laboratorio.universidad.entity.PlanEstudios;
import com.laboratorio.universidad.entity.PeriodoAcademico;
import com.laboratorio.universidad.entity.Paralelo;
import com.laboratorio.universidad.entity.Horario;
import com.laboratorio.universidad.entity.DiaSemana;
import com.laboratorio.universidad.exception.EntidadNoEncontradaException;
import com.laboratorio.universidad.exception.ReglaNegocioException;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.time.LocalTime;

public class AcademicService {
    private final EntityManager entityManager;

    public AcademicService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Docente> docentes() { return entityManager.createQuery("SELECT d FROM Docente d ORDER BY d.apellido, d.nombre", Docente.class).getResultList(); }
    public List<Carrera> carreras() { return entityManager.createQuery("SELECT c FROM Carrera c ORDER BY c.nombre", Carrera.class).getResultList(); }
    public List<Materia> materias() { return entityManager.createQuery("SELECT m FROM Materia m ORDER BY m.nombre", Materia.class).getResultList(); }
    public List<PlanEstudios> planes() { return safeList("SELECT p FROM PlanEstudios p ORDER BY p.codigo", PlanEstudios.class); }
    public List<PeriodoAcademico> periodos() { return safeList("SELECT p FROM PeriodoAcademico p ORDER BY p.gestion DESC, p.nombre", PeriodoAcademico.class); }
    public List<Aula> aulas() { return safeList("SELECT a FROM Aula a ORDER BY a.codigo", Aula.class); }
    public List<Paralelo> paralelos() { return safeList("SELECT p FROM Paralelo p ORDER BY p.codigo", Paralelo.class); }
    public List<Horario> horarios() { return safeList("SELECT h FROM Horario h ORDER BY h.diaSemana, h.horaInicio", Horario.class); }

    public Docente crear(DocenteRequest request) {
        validar(request.getCi(), request.getNombre(), request.getApellido(), request.getCorreo(), request.getCodigoDocente());
        return TransactionExecutor.execute(entityManager, () -> {
            Docente docente = new Docente(request.getCi(), request.getNombre(), request.getApellido(), request.getCorreo(), request.getCodigoDocente());
            docente.setEspecialidad(request.getEspecialidad()); docente.setGradoAcademico(request.getGradoAcademico());
            entityManager.persist(docente); return docente;
        });
    }

    public Docente actualizarDocente(Long id, DocenteRequest request) {
        validar(request.getCi(), request.getNombre(), request.getApellido(), request.getCorreo(), request.getCodigoDocente());
        return TransactionExecutor.execute(entityManager, () -> {
            Docente docente = require(Docente.class, id);
            docente.setCi(request.getCi()); docente.setNombre(request.getNombre()); docente.setApellido(request.getApellido());
            docente.setCorreo(request.getCorreo()); docente.setCodigoDocente(request.getCodigoDocente());
            docente.setEspecialidad(request.getEspecialidad()); docente.setGradoAcademico(request.getGradoAcademico()); return docente;
        });
    }

    public Carrera crear(CarreraRequest request) {
        validar(request.getNombre(), request.getCodigo());
        if (request.getDuracionSemestres() <= 0) throw new ReglaNegocioException("La duración debe ser positiva");
        return TransactionExecutor.execute(entityManager, () -> {
            Carrera carrera = new Carrera(request.getNombre(), request.getCodigo(), request.getDuracionSemestres());
            carrera.setGradoAcademico(request.getGradoAcademico()); carrera.setModalidad(request.getModalidad()); entityManager.persist(carrera); return carrera;
        });
    }

    public Carrera actualizarCarrera(Long id, CarreraRequest request) {
        validar(request.getNombre(), request.getCodigo());
        return TransactionExecutor.execute(entityManager, () -> {
            Carrera carrera = require(Carrera.class, id); carrera.setNombre(request.getNombre()); carrera.setCodigo(request.getCodigo());
            carrera.setGradoAcademico(request.getGradoAcademico()); carrera.setDuracionSemestres(request.getDuracionSemestres()); carrera.setModalidad(request.getModalidad()); return carrera;
        });
    }

    public Materia crear(MateriaRequest request) {
        validar(request.getCodigo(), request.getNombre());
        if (request.getCreditos() < 0) throw new ReglaNegocioException("Los créditos no pueden ser negativos");
        return TransactionExecutor.execute(entityManager, () -> {
            Materia materia = new Materia(request.getCodigo(), request.getNombre(), request.getCreditos(), request.getSemestre());
            materia.setDescripcion(request.getDescripcion()); materia.setHorasTeoricas(request.getHorasTeoricas()); materia.setHorasPracticas(request.getHorasPracticas()); entityManager.persist(materia); return materia;
        });
    }

    public Materia actualizarMateria(Long id, MateriaRequest request) {
        validar(request.getCodigo(), request.getNombre());
        return TransactionExecutor.execute(entityManager, () -> {
            Materia materia = require(Materia.class, id); materia.setCodigo(request.getCodigo()); materia.setNombre(request.getNombre());
            materia.setDescripcion(request.getDescripcion()); materia.setCreditos(request.getCreditos()); materia.setSemestre(request.getSemestre());
            materia.setHorasTeoricas(request.getHorasTeoricas()); materia.setHorasPracticas(request.getHorasPracticas()); return materia;
        });
    }

    public PlanEstudios crear(PlanEstudiosRequest request) {
        validar(request.getCodigo(), request.getVersion());
        return TransactionExecutor.execute(entityManager, () -> {
            PlanEstudios plan = new PlanEstudios(request.getCodigo(), request.getVersion(), request.getFechaInicio());
            plan.setFechaFin(request.getFechaFin());
            if (request.getCarreraId() != null) plan.setCarrera(require(Carrera.class, request.getCarreraId()));
            entityManager.persist(plan); return plan;
        });
    }

    public PeriodoAcademico crear(PeriodoRequest request) {
        validar(request.getNombre());
        if (request.getGestion() <= 0 || request.getFechaInicio() == null || request.getFechaFin() == null) throw new ReglaNegocioException("Periodo incompleto");
        return TransactionExecutor.execute(entityManager, () -> {
            PeriodoAcademico periodo = new PeriodoAcademico(request.getNombre(), request.getGestion(), request.getFechaInicio(), request.getFechaFin());
            if (request.getEstado() != null) periodo.setEstado(request.getEstado());
            entityManager.persist(periodo); return periodo;
        });
    }

    public Aula crear(AulaRequest request) {
        validar(request.getCodigo(), request.getEdificio());
        if (request.getCapacidad() <= 0 || request.getTipo() == null) throw new ReglaNegocioException("Aula incompleta");
        return TransactionExecutor.execute(entityManager, () -> {
            Aula aula = new Aula(request.getCodigo(), request.getEdificio(), request.getPiso(), request.getCapacidad(), request.getTipo());
            entityManager.persist(aula); return aula;
        });
    }

    public Paralelo crear(ParaleloRequest request) {
        validar(request.getCodigo());
        if (request.getCupoMaximo() <= 0) throw new ReglaNegocioException("El cupo máximo debe ser positivo");
        return TransactionExecutor.execute(entityManager, () -> {
            Materia materia = require(Materia.class, request.getMateriaId());
            PeriodoAcademico periodo = require(PeriodoAcademico.class, request.getPeriodoId());
            Paralelo paralelo = new Paralelo(request.getCodigo(), request.getCupoMaximo(), materia, periodo);
            if (request.getDocenteId() != null) paralelo.setDocente(require(Docente.class, request.getDocenteId()));
            if (request.getAulaId() != null) paralelo.setAula(require(Aula.class, request.getAulaId()));
            entityManager.persist(paralelo); return paralelo;
        });
    }

    public Horario crear(HorarioRequest request) {
        if (request.getDiaSemana() == null || request.getHoraInicio() == null || request.getHoraFin() == null
                || request.getParaleloId() == null || request.getAulaId() == null || !request.getHoraInicio().isBefore(request.getHoraFin())) {
            throw new ReglaNegocioException("Horario incompleto o inválido");
        }
        return TransactionExecutor.execute(entityManager, () -> {
            Paralelo paralelo = require(Paralelo.class, request.getParaleloId());
            Aula aula = require(Aula.class, request.getAulaId());
            validarCruce(aula, request.getDiaSemana(), request.getHoraInicio(), request.getHoraFin());
            Horario horario = new Horario(request.getDiaSemana(), request.getHoraInicio(), request.getHoraFin(), aula);
            horario.setParalelo(paralelo); entityManager.persist(horario); return horario;
        });
    }

    private void validarCruce(Aula aula, DiaSemana dia, LocalTime inicio, LocalTime fin) {
        try {
            Long cruces = entityManager.createQuery("SELECT COUNT(h) FROM Horario h WHERE h.aula = :aula AND h.diaSemana = :dia "
                            + "AND h.horaInicio < :fin AND h.horaFin > :inicio", Long.class)
                    .setParameter("aula", aula).setParameter("dia", dia).setParameter("fin", fin).setParameter("inicio", inicio)
                    .getSingleResult();
            if (cruces > 0) throw new ReglaNegocioException("El aula ya está ocupada en ese horario");
        } catch (ReglaNegocioException exception) {
            throw exception;
        } catch (RuntimeException exception) {
            throw new ReglaNegocioException("No se pudo verificar la disponibilidad del aula");
        }
    }

    public void eliminar(Class<?> type, Long id) {
        TransactionExecutor.execute(entityManager, () -> { entityManager.remove(require(type, id)); return null; });
    }

    private <T> T require(Class<T> type, Long id) {
        T entity = entityManager.find(type, id);
        if (entity == null) throw new EntidadNoEncontradaException(type.getSimpleName() + " no encontrado: " + id);
        return entity;
    }

    private void validar(String... values) {
        for (String value : values) if (value == null || value.isBlank()) throw new ReglaNegocioException("Los campos obligatorios no pueden estar vacíos");
    }

    private <T> List<T> safeList(String jpql, Class<T> type) {
        try {
            return entityManager.createQuery(jpql, type).getResultList();
        } catch (RuntimeException exception) {
            return List.of();
        }
    }
}
