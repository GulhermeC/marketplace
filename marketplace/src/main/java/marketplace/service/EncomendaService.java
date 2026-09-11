package marketplace.service;

import marketplace.model.Encomenda;
import marketplace.model.Utilizador;
import marketplace.repository.EncomendaRepository;
import marketplace.repository.ProdutoRepository;
import marketplace.repository.UtilizadorRepository;
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

    public List<Encomenda> getAll() {
        return encomendaRepository.getAll();
    }

    public Encomenda getById(int id) {
        return encomendaRepository.getById(id);
    }

    @Transactional 
    public Encomenda create(String estado, int preco_total, int idComprador)
    {
        Utilizador comprador = utilizadorRepository.getById(idComprador);
        if (comprador == null) return null;

        Encomenda encomenda = new Encomenda();
        encomenda.setEstado(estado);
        encomenda.setPreco_total(preco_total);
        encomenda.setComprador(comprador);
        encomendaRepository.create(encomenda);
        return encomenda;
    }

    @Transactional 
    public Encomenda update(int id, String estado, int preco_total)
    {
        return encomendaRepository.update(id, estado, preco_total);
    }

    @Transactional 
    public boolean delete(int id){
        return encomendaRepository.delete(id);
    }
}
