package com.laboratorio.universidad;

import com.laboratorio.universidad.entity.Carrera;
import com.laboratorio.universidad.entity.Estudiante;
import com.laboratorio.universidad.entity.EstadoAcademico;
import com.laboratorio.universidad.entity.Facultad;
import com.laboratorio.universidad.entity.Materia;
import com.laboratorio.universidad.entity.PlanEstudios;
import com.laboratorio.universidad.entity.PlanMateria;
import com.laboratorio.universidad.entity.Universidad;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntidadesUniversidadTest {
    @Test
    void modeloInstitucionalMantieneAmbosLados() {
        Universidad universidad = new Universidad("Universidad Tecnológica Nacional", "UTN");
        Facultad facultad = new Facultad("Facultad de Ingeniería", "FI");
        Carrera carrera = new Carrera("Ingeniería de Sistemas", "SIS", 10);
        PlanEstudios plan = new PlanEstudios("SIS-2026", "1", LocalDate.of(2026, 1, 1));
        Materia materia = new Materia("BDO-101", "Bases de Datos I", 5, 4);

        universidad.addFacultad(facultad);
        facultad.addCarrera(carrera);
        carrera.addPlanEstudios(plan);
        plan.addMateria(new PlanMateria(materia, 4, 5, true, false));

        assertEquals(universidad, facultad.getUniversidad());
        assertEquals(facultad, carrera.getFacultad());
        assertEquals(carrera, plan.getCarrera());
        assertEquals(1, plan.getMaterias().size());
    }

    @Test
    void estudianteEsUnaPersonaEspecializada() {
        Estudiante estudiante = new Estudiante("123", "Ana", "Gómez", "ana@example.com", "2026001");
        estudiante.setEstadoAcademico(EstadoAcademico.ACTIVO);

        assertEquals("Ana", estudiante.getNombre());
        assertEquals(EstadoAcademico.ACTIVO, estudiante.getEstadoAcademico());
    }
}
