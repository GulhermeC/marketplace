package marketplace.controller;

import marketplace.service.EncomendaService;
import marketplace.model.Encomenda;
import marketplace.model.EncomendaDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Path("/encomendas")
public class EncomendaController {
    @Inject
    private EncomendaService encomendaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Encomenda> encomendas = encomendaService.getAll();
        return Response.ok(encomendas).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        Encomenda encomenda = encomendaService.getById(id);
        if (encomenda == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(encomenda).build();
    }

    @GET
    @Path("/comprador/{idComprador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByComprador(
            @PathParam("idComprador") int idComprador) {
        List<EncomendaDTO> encomendas = encomendaService.getByCompradorDTO(idComprador);

        return Response.ok(encomendas).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @QueryParam("estado") String estado,
            @QueryParam("idComprador") int idComprador,
            @QueryParam("idProduto") List<Integer> idProdutos) {
        Encomenda encomenda = encomendaService.create(estado, idComprador, idProdutos);

        if (encomenda == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(
            @PathParam("id") int id,
            @QueryParam("estado") String estado) {
        Encomenda encomenda = encomendaService.update(id, estado);
        if (encomenda == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(encomenda).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        boolean deleted = encomendaService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
