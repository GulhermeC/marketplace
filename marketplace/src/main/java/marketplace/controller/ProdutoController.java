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

    @POST
    public Response create(
        @QueryParam("nome") String nome
    )
    {
        Produto produto = produtoService.create(nome);
        return Response.status(Response.Status.CREATED).entity(produto).build();
    }
}
