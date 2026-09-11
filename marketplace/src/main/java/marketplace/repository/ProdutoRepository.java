package marketplace.repository;

import marketplace.model.Produto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class ProdutoRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Produto> getAll() {
        return em.createQuery("select p from Produto p", Produto.class).getResultList();
    }

    public Produto getById(int id) {
        return em.find(Produto.class, id);
    }

    public void create(Produto produto) {
        em.persist(produto);
    }

    public Produto update(int id, String nome, float preco, int stock, String categoria) {
        Produto existente = em.find(Produto.class, id);
        if (existente == null) return null;

        existente.setNome(nome);
        existente.setPreco(preco);
        existente.setStock(stock);
        existente.setCategoria(categoria);

        return existente;
    }

    public boolean delete(int id) {
        Produto produto = em.find(Produto.class, id);
        if (produto == null) return false;
        em.remove(produto);
        return true;
    }
}
