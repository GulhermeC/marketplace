package marketplace.service;

import marketplace.model.Produto;
import marketplace.model.Utilizador;
import marketplace.repository.ProdutoRepository;
import marketplace.repository.UtilizadorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProdutoService {
    @Inject
    private ProdutoRepository produtoRepository;

    @Inject 
    private UtilizadorRepository utilizadorRepository;

    public List<Produto> getAll() {
        return produtoRepository.getAll();
    }

    public Produto getById(int id) {
        return produtoRepository.getById(id);
    }

    @Transactional
    public Produto create(String nome, float preco, int stock, String categoria, int idVendedor) {

        Utilizador vendedor = utilizadorRepository.getById(idVendedor);
        if (vendedor == null) return null;

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setStock(stock);
        produto.setCategoria(categoria);
        produto.setVendedor(vendedor);
        produtoRepository.create(produto);
        return produto;
    }

    @Transactional
    public Produto update(int id, String nome, float preco, int stock, String categoria) {
        return produtoRepository.update(id, nome, preco, stock, categoria);
    }

    @Transactional
    public boolean delete(int id) {
        return produtoRepository.delete(id);
    }
}
