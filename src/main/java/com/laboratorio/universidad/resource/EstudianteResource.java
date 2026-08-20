package com.laboratorio.universidad.resource;

import com.laboratorio.universidad.dto.EstudianteDTO;
import com.laboratorio.universidad.dto.EstudianteRequest;
import com.laboratorio.universidad.entity.Estudiante;
import com.laboratorio.universidad.service.EstudianteService;
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

import jakarta.persistence.EntityManager;
import java.util.List;

@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteResource {
    private final EntityManager entityManager;
    private final EstudianteService service;

    public EstudianteResource(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.service = new EstudianteService(entityManager);
    }

    @GET
    public List<EstudianteDTO> listar() {
        return entityManager.createQuery("SELECT e FROM Estudiante e ORDER BY e.apellido, e.nombre", Estudiante.class)
                .getResultList().stream().map(EstudianteDTO::new).toList();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        Estudiante estudiante = entityManager.find(Estudiante.class, id);
        return estudiante == null
                ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(new EstudianteDTO(estudiante)).build();
    }

    @POST
    public Response crear(@Valid EstudianteRequest request) {
        return Response.status(Response.Status.CREATED)
                .entity(new EstudianteDTO(service.crear(request))).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, @Valid EstudianteRequest request) {
        return Response.ok(new EstudianteDTO(service.actualizar(id, request))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        service.eliminar(id);
        return Response.noContent().build();
    }
}
