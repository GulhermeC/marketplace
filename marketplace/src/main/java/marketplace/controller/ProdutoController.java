package marketplace.controller;

import marketplace.model.Produto;
import marketplace.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/produtos")
public class ProdutoController {
    @Inject 
    private ProdutoService produtoService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Produto> produtos = produtoService.getAll();
        return Response.ok(produtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Produto produto = produtoService.getById(id);
        if (produto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(produto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
        @QueryParam("nome") String nome,
        @QueryParam("preco") float preco,
        @QueryParam("stock") int stock,
        @QueryParam("categoria") String categoria,
        @QueryParam("idVendedor") int idVendedor
    )
    {
        Produto produto = produtoService.create(nome, preco, stock, categoria, idVendedor);

        if (produto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.CREATED).entity(produto).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
        @PathParam("id") int id,
        @QueryParam("nome") String nome,
        @QueryParam("preco") float preco,
        @QueryParam("stock") int stock,
        @QueryParam("categoria") String categoria
    ) {
        Produto atualizado = produtoService.update(id, nome, preco, stock, categoria);
        if (atualizado == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        boolean deleted = produtoService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
