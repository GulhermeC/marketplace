package marketplace.service;

import marketplace.model.Encomenda;
import marketplace.model.LinhaEncomenda;
import marketplace.model.Produto;
import marketplace.model.Utilizador;
import marketplace.model.EncomendaDTO;
import marketplace.model.LinhaEncomendaDTO;
import marketplace.repository.EncomendaRepository;
import marketplace.repository.UtilizadorRepository;
import marketplace.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Encomenda> getByComprador(int idComprador) {
        return encomendaRepository.getByComprador(idComprador);
    }

    public List<EncomendaDTO> getByCompradorDTO(int idComprador) {

        List<Encomenda> encomendas = encomendaRepository.getByComprador(idComprador);

        return encomendas.stream()
            .map(e -> {

                EncomendaDTO dto = new EncomendaDTO();

                dto.setId(e.getId());
                dto.setEstado(e.getEstado());
                dto.setPreco_total(e.getPreco_total());

                List<LinhaEncomendaDTO> linhas = e.getLinhas().stream()
                    .map(linha -> {

                        LinhaEncomendaDTO linhaDTO = new LinhaEncomendaDTO();

                        linhaDTO.setIdProduto(linha.getProduto().getId());
                        linhaDTO.setNomeProduto(linha.getProduto().getNome());
                        linhaDTO.setQuantidade(linha.getQuantidade());
                        linhaDTO.setPreco(linha.getPreco());

                        return linhaDTO;
                    })
                    .toList();

                dto.setLinhas(linhas);

                return dto;
            })
            .toList();
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

        Map<Integer, Integer> quantidades = new HashMap<>();

        for (Integer idProduto : idProdutos) {
            quantidades.put(
                idProduto,
                quantidades.getOrDefault(idProduto, 0) + 1
            );
        }

        for (Map.Entry<Integer, Integer> entry : quantidades.entrySet()) {

            Produto produto = produtoRepository.getById(entry.getKey());

            if (produto == null) {
                return null;
            }

            LinhaEncomenda linha = new LinhaEncomenda();

            linha.setEncomenda(encomenda);
            linha.setProduto(produto);
            linha.setQuantidade(entry.getValue());
            linha.setPreco(produto.getPreco());

            encomenda.getLinhas().add(linha);

            preco_total += produto.getPreco() * entry.getValue();
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
