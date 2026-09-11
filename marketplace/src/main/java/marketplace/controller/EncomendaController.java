package marketplace.controller;

import marketplace.service.EncomendaService;
import marketplace.model.Encomenda;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/encomendas")
public class EncomendaController {
    @Inject
    private EncomendaService encomendaService;

    @GET 
    public Response getAll() {
        List<Encomenda> encomendas = encomendaService.getAll();
        return Response.ok(encomendas).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        Encomenda encomenda = encomendaService.getById(id);
        if (encomenda == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(encomenda).build();
    }

    @POST 
    public Response create(
        @QueryParam ("estado") String estado,
        @QueryParam ("preco_total") float preco_total,
        @QueryParam ("idComprador") int idComprador,
        @QueryParam("idProduto") List<Integer> idProdutos
    )
    {
        Encomenda encomenda = encomendaService.create(estado, preco_total, idComprador, idProdutos);

        if (encomenda == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.CREATED).entity(encomenda).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(
        @PathParam("id") int id,
        @QueryParam ("estado") String estado,
        @QueryParam ("preco_total") float preco_total
    )
    {
        Encomenda encomenda = encomendaService.update(id, estado, preco_total);
        if (encomenda == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(encomenda).build();
    }

    @DELETE 
    @Path("/{id}")
    public Response delete(@PathParam ("id") int id) {
        boolean deleted = encomendaService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
