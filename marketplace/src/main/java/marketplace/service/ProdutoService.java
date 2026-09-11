package marketplace.service;

import marketplace.model.Produto;
import marketplace.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProdutoService {
    @Inject
    private ProdutoRepository produtoRepository;

    public List<Produto> getAll() {
        return produtoRepository.getAll();
    }

    public Produto getById(int id) {
        return produtoRepository.getById(id);
    }

    @Transactional
    public Produto create(String nome, float preco, int stock, String categoria) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setStock(stock);
        produto.setCategoria(categoria);
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
