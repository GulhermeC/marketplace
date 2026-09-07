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

    @Transactional
    public Produto create(String nome) {
        Produto produto = new Produto(nome);
        produtoRepository.create(produto);
        return produto;
    }
}
