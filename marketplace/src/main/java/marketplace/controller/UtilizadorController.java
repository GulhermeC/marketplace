package marketplace.controller;

import marketplace.model.Utilizador;
import marketplace.service.UtilizadorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/utilizadores")
public class UtilizadorController {
    @Inject 
    private UtilizadorService utilizadorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Utilizador> utilizadores = utilizadorService.getAll();
        return Response.ok(utilizadores).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        Utilizador utilizador = utilizadorService.getById(id);
        if (utilizador == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(utilizador).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
        @QueryParam("nome") String nome,
        @QueryParam("password") String password,
        @QueryParam("email") String email,
        @QueryParam("numTele") int numTele,
        @QueryParam("local") String local,
        @QueryParam("tipo") String tipo
    )
    {
        Utilizador utilizador = utilizadorService.create(nome, password, email, numTele, local, tipo);
        return Response.status(Response.Status.CREATED).entity(utilizador).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
        @PathParam("id") int id,
        @QueryParam("nome") String nome,
        @QueryParam("password") String password,
        @QueryParam("email") String email,
        @QueryParam("numTele") int numTele,
        @QueryParam("local") String local,
        @QueryParam("tipo") String tipo
    )
    {
        Utilizador atualizado = utilizadorService.update(id, nome, password, email, numTele, local, tipo);
        if (atualizado == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        boolean deleted = utilizadorService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
        @QueryParam("email") String email,
        @QueryParam("password") String password
    ) {
        Utilizador utilizador = utilizadorService.login(email, password);

        if (utilizador == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.ok(utilizador).build();
    }
}
