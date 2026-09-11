package marketplace.service;

import marketplace.model.Encomenda;
import marketplace.model.Produto;
import marketplace.model.Utilizador;
import marketplace.repository.EncomendaRepository;
import marketplace.repository.UtilizadorRepository;
import marketplace.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped 
public class EncomendaService {
    @Inject
    private EncomendaRepository encomendaRepository;

    @Inject
    private UtilizadorRepository utilizadorRepository;

    @Inject 
    private ProdutoRepository produtoRepository;

    public List<Encomenda> getAll() {
        return encomendaRepository.getAll();
    }

    public Encomenda getById(int id) {
        return encomendaRepository.getById(id);
    }

    @Transactional 
    public Encomenda create(String estado, float preco_total, int idComprador, List<Integer> idProdutos)
    {
        Utilizador comprador = utilizadorRepository.getById(idComprador);
        if (comprador == null) return null;

        Encomenda encomenda = new Encomenda();
        encomenda.setEstado(estado);
        encomenda.setPreco_total(preco_total);
        encomenda.setComprador(comprador);

        for (Integer idProduto : idProdutos) {
            Produto produto = produtoRepository.getById(idProduto);

            if (produto == null) {
                return null;
            }

            encomenda.getProdutos().add(produto);
        }

        encomendaRepository.create(encomenda);
        return encomenda;
    }

    @Transactional 
    public Encomenda update(int id, String estado, float preco_total)
    {
        return encomendaRepository.update(id, estado, preco_total);
    }

    @Transactional 
    public boolean delete(int id){
        return encomendaRepository.delete(id);
    }
}
