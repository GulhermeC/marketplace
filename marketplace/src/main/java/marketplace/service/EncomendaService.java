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
    public Encomenda create(String estado, int idComprador, List<Integer> idProdutos)
    {
        Utilizador comprador = utilizadorRepository.getById(idComprador);
        if (comprador == null) return null;

        Encomenda encomenda = new Encomenda();
        encomenda.setEstado(estado);
        encomenda.setComprador(comprador);

        float preco_total = 0;

        for (Integer idProduto : idProdutos) {
            Produto produto = produtoRepository.getById(idProduto);

            if (produto == null) {
                return null;
            }

            encomenda.getProdutos().add(produto);

            preco_total += produto.getPreco();
        }

        encomenda.setPreco_total(preco_total);

        encomendaRepository.create(encomenda);
        return encomenda;
    }

    @Transactional 
    public Encomenda update(int id, String estado)
    {
        return encomendaRepository.update(id, estado);
    }

    @Transactional 
    public boolean delete(int id){
        return encomendaRepository.delete(id);
    }
}
