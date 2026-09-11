package marketplace.controller;

import marketplace.model.Produto;
import marketplace.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/produtos")
public class ProdutoController {
    @Inject 
    private ProdutoService produtoService;
    
    @GET
    public Response getAll() {
        List<Produto> produtos = produtoService.getAll();
        return Response.ok(produtos).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        Produto produto = produtoService.getById(id);
        if (produto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(produto).build();
    }

    @POST
    public Response create(
        @QueryParam("nome") String nome,
        @QueryParam("preco") float preco,
        @QueryParam("stock") int stock,
        @QueryParam("categoria") String categoria
    )
    {
        Produto produto = produtoService.create(nome, preco, stock, categoria);
        return Response.status(Response.Status.CREATED).entity(produto).build();
    }

    @PUT
    @Path("/{id}")
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
