package marketplace.repository;

import marketplace.model.Encomenda;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class EncomendaRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Encomenda> getAll() {
        return em.createQuery("select p from Encomenda p", Encomenda.class).getResultList();
    }

    public Encomenda getById(int id) {
        return em.find(Encomenda.class, id);
    }

    public List<Encomenda> getByComprador(int idComprador) {
        return em.createQuery(
            "select e from Encomenda e where e.comprador.id = :idComprador",
            Encomenda.class
        )
        .setParameter("idComprador", idComprador)
        .getResultList();
    }

    public void create(Encomenda encomenda) {
        em.persist(encomenda);
    }

    public Encomenda update(int id, String estado) {
        Encomenda existente = em.find(Encomenda.class, id);
        if (existente == null) return null;

        existente.setEstado(estado);

        return existente;
    }

    public boolean delete(int id) {
        Encomenda encomenda = em.find(Encomenda.class, id);
        if (encomenda == null) return false;
        em.remove(encomenda);
        return true;
    }
}
