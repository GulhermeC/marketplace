package marketplace.repository;

import marketplace.model.Utilizador;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped 
public class UtilizadorRepository {
    @PersistenceContext 
    private EntityManager em;

    public List<Utilizador> getAll() {
        return em.createQuery("select p from Utilizador p", Utilizador.class).getResultList();
    }

    public Utilizador getById(int id) {
        return em.find(Utilizador.class, id);
    }

    public Utilizador getByEmail(String email) {
        return em.createQuery(
            "select u from Utilizador u where u.email = :email",
            Utilizador.class
        )
        .setParameter("email", email)
        .getResultStream()
        .findFirst()
        .orElse(null);
    }

    public void create(Utilizador utilizador) {
        em.persist(utilizador);
    }

    public Utilizador update(int id, String nome, String password, String email, int numTele, String local, String tipo){
        Utilizador existente = em.find(Utilizador.class, id);
        if (existente == null) return null;

        existente.setNome(nome);
        existente.setPassword(password);
        existente.setEmail(email);
        existente.setNumTele(numTele);
        existente.setLocal(local);
        existente.setTipo(tipo);

        return existente;
    }

    public boolean delete(int id) {
        Utilizador utilizador = em.find(Utilizador.class, id);
        if (utilizador == null) return false;
        em.remove(utilizador);
        return true;
    }
}
