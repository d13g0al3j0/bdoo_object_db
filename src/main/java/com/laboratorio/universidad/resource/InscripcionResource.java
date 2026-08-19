package com.laboratorio.universidad.resource;

import com.laboratorio.universidad.dto.ErrorResponse;
import com.laboratorio.universidad.dto.InscripcionRequest;
import com.laboratorio.universidad.dto.InscripcionResponse;
import com.laboratorio.universidad.entity.Inscripcion;
import com.laboratorio.universidad.entity.Usuario;
import com.laboratorio.universidad.exception.ReglaNegocioException;
import com.laboratorio.universidad.service.InscripcionService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/inscripciones")
public class InscripcionResource {
    private final EntityManager entityManager;
    private final InscripcionService service;

    public InscripcionResource(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.service = new InscripcionService(entityManager);
    }

    @POST
    @Path("/transaccion")
    public Response registrar(@Valid InscripcionRequest request) {
        try {
            Usuario usuario = request.getUsuarioId() == null ? null : entityManager.find(Usuario.class, request.getUsuarioId());
            Inscripcion inscripcion = service.registrar(request.getEstudianteId(), request.getPeriodoId(), request.getParalelos(), usuario);
            return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_JSON)
                    .entity(new InscripcionResponse(inscripcion)).build();
        } catch (ReglaNegocioException | IllegalStateException exception) {
            return Response.status(Response.Status.CONFLICT).type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorResponse(409, exception.getClass().getSimpleName(), exception.getMessage(), "ROLLED_BACK"))
                    .build();
        }
    }
}
