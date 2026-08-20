package com.laboratorio.universidad.resource;

import com.laboratorio.universidad.dto.CarreraDTO;
import com.laboratorio.universidad.dto.CarreraRequest;
import com.laboratorio.universidad.dto.DocenteDTO;
import com.laboratorio.universidad.dto.DocenteRequest;
import com.laboratorio.universidad.dto.MateriaDTO;
import com.laboratorio.universidad.dto.MateriaRequest;
import com.laboratorio.universidad.dto.PlanEstudiosDTO;
import com.laboratorio.universidad.dto.PlanEstudiosRequest;
import com.laboratorio.universidad.dto.PeriodoDTO;
import com.laboratorio.universidad.dto.PeriodoRequest;
import com.laboratorio.universidad.dto.AulaDTO;
import com.laboratorio.universidad.dto.AulaRequest;
import com.laboratorio.universidad.dto.ParaleloDTO;
import com.laboratorio.universidad.dto.ParaleloRequest;
import com.laboratorio.universidad.dto.HorarioDTO;
import com.laboratorio.universidad.dto.HorarioRequest;
import com.laboratorio.universidad.entity.Carrera;
import com.laboratorio.universidad.entity.Docente;
import com.laboratorio.universidad.entity.Materia;
import com.laboratorio.universidad.service.AcademicService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AcademicResource {
    private final AcademicService service;

    public AcademicResource(EntityManager entityManager) {
        this.service = new AcademicService(entityManager);
    }

    @GET @Path("docentes")
    public Object docentes() { return service.docentes().stream().map(DocenteDTO::new).toList(); }
    @POST @Path("docentes")
    public Response crearDocente(@Valid DocenteRequest request) { return Response.status(201).entity(new DocenteDTO(service.crear(request))).build(); }
    @PUT @Path("docentes/{id}")
    public DocenteDTO actualizarDocente(@PathParam("id") Long id, @Valid DocenteRequest request) { return new DocenteDTO(service.actualizarDocente(id, request)); }
    @DELETE @Path("docentes/{id}")
    public Response eliminarDocente(@PathParam("id") Long id) { service.eliminar(Docente.class, id); return Response.noContent().build(); }

    @GET @Path("carreras")
    public Object carreras() { return service.carreras().stream().map(CarreraDTO::new).toList(); }
    @POST @Path("carreras")
    public Response crearCarrera(@Valid CarreraRequest request) { return Response.status(201).entity(new CarreraDTO(service.crear(request))).build(); }
    @PUT @Path("carreras/{id}")
    public CarreraDTO actualizarCarrera(@PathParam("id") Long id, @Valid CarreraRequest request) { return new CarreraDTO(service.actualizarCarrera(id, request)); }
    @DELETE @Path("carreras/{id}")
    public Response eliminarCarrera(@PathParam("id") Long id) { service.eliminar(Carrera.class, id); return Response.noContent().build(); }

    @GET @Path("materias")
    public Object materias() { return service.materias().stream().map(MateriaDTO::new).toList(); }
    @POST @Path("materias")
    public Response crearMateria(@Valid MateriaRequest request) { return Response.status(201).entity(new MateriaDTO(service.crear(request))).build(); }
    @PUT @Path("materias/{id}")
    public MateriaDTO actualizarMateria(@PathParam("id") Long id, @Valid MateriaRequest request) { return new MateriaDTO(service.actualizarMateria(id, request)); }
    @DELETE @Path("materias/{id}")
    public Response eliminarMateria(@PathParam("id") Long id) { service.eliminar(Materia.class, id); return Response.noContent().build(); }

    @GET @Path("planes-estudios")
    public Object planes() { return service.planes().stream().map(PlanEstudiosDTO::new).toList(); }
    @POST @Path("planes-estudios")
    public Response crearPlan(@Valid PlanEstudiosRequest request) { return Response.status(201).entity(new PlanEstudiosDTO(service.crear(request))).build(); }

    @GET @Path("periodos")
    public Object periodos() { return service.periodos().stream().map(PeriodoDTO::new).toList(); }
    @POST @Path("periodos")
    public Response crearPeriodo(@Valid PeriodoRequest request) { return Response.status(201).entity(new PeriodoDTO(service.crear(request))).build(); }

    @GET @Path("aulas")
    public Object aulas() { return service.aulas().stream().map(AulaDTO::new).toList(); }
    @POST @Path("aulas")
    public Response crearAula(@Valid AulaRequest request) { return Response.status(201).entity(new AulaDTO(service.crear(request))).build(); }

    @GET @Path("paralelos")
    public Object paralelos() { return service.paralelos().stream().map(ParaleloDTO::new).toList(); }
    @POST @Path("paralelos")
    public Response crearParalelo(@Valid ParaleloRequest request) { return Response.status(201).entity(new ParaleloDTO(service.crear(request))).build(); }

    @GET @Path("horarios")
    public Object horarios() { return service.horarios().stream().map(HorarioDTO::new).toList(); }
    @POST @Path("horarios")
    public Response crearHorario(@Valid HorarioRequest request) { return Response.status(201).entity(new HorarioDTO(service.crear(request))).build(); }
}
