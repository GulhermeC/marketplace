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

    public Produto getById(Long id) {
        return em.find(Produto.class, id);
    }

    public void create(Produto produto) {
        em.persist(produto);
    }
}
