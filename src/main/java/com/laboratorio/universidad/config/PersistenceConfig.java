package com.laboratorio.universidad.config;

import com.laboratorio.universidad.entity.Administrador;
import com.laboratorio.universidad.entity.Asistencia;
import com.laboratorio.universidad.entity.Auditoria;
import com.laboratorio.universidad.entity.Aula;
import com.laboratorio.universidad.entity.Calificacion;
import com.laboratorio.universidad.entity.Carrera;
import com.laboratorio.universidad.entity.ConceptoPago;
import com.laboratorio.universidad.entity.DetalleInscripcion;
import com.laboratorio.universidad.entity.Docente;
import com.laboratorio.universidad.entity.Estudiante;
import com.laboratorio.universidad.entity.Evaluacion;
import com.laboratorio.universidad.entity.Facultad;
import com.laboratorio.universidad.entity.Horario;
import com.laboratorio.universidad.entity.Inscripcion;
import com.laboratorio.universidad.entity.Materia;
import com.laboratorio.universidad.entity.Paralelo;
import com.laboratorio.universidad.entity.Pago;
import com.laboratorio.universidad.entity.Persona;
import com.laboratorio.universidad.entity.PlanEstudios;
import com.laboratorio.universidad.entity.PlanMateria;
import com.laboratorio.universidad.entity.PeriodoAcademico;
import com.laboratorio.universidad.entity.Rol;
import com.laboratorio.universidad.entity.Universidad;
import com.laboratorio.universidad.entity.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceConfiguration;

public final class PersistenceConfig {
    private PersistenceConfig() {
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        PersistenceConfiguration configuration = new PersistenceConfiguration("universidadPU")
                .property("jakarta.persistence.jdbc.url", "objectdb:data/universidad.odb")
                .managedClass(Persona.class)
                .managedClass(Estudiante.class)
                .managedClass(Docente.class)
                .managedClass(Administrador.class)
                .managedClass(Universidad.class)
                .managedClass(Facultad.class)
                .managedClass(Carrera.class)
                .managedClass(PlanEstudios.class)
                .managedClass(Materia.class)
                .managedClass(PlanMateria.class)
                .managedClass(PeriodoAcademico.class)
                .managedClass(Paralelo.class)
                .managedClass(Aula.class)
                .managedClass(Horario.class)
                .managedClass(Inscripcion.class)
                .managedClass(DetalleInscripcion.class)
                .managedClass(Evaluacion.class)
                .managedClass(Calificacion.class)
                .managedClass(Asistencia.class)
                .managedClass(Pago.class)
                .managedClass(ConceptoPago.class)
                .managedClass(Usuario.class)
                .managedClass(Rol.class)
                .managedClass(Auditoria.class);

        return Persistence.createEntityManagerFactory(configuration);
    }
}
