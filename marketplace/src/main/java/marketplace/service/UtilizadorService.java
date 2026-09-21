package marketplace.service;

import marketplace.model.Utilizador;
import marketplace.repository.UtilizadorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped 
public class UtilizadorService {
    @Inject 
    private UtilizadorRepository utilizadorRepository;

    public List<Utilizador> getAll() {
        return utilizadorRepository.getAll();
    }

    public Utilizador getById(int id) {
        return utilizadorRepository.getById(id);
    }

    @Transactional
    public Utilizador create(String nome, String password, String email, int numTele, String local, String tipo) {
        Utilizador utilizador = new Utilizador();
        utilizador.setNome(nome);
        utilizador.setPassword(password);
        utilizador.setEmail(email);
        utilizador.setNumTele(numTele);
        utilizador.setLocal(local);
        utilizador.setTipo(tipo);
        utilizadorRepository.create(utilizador);
        return utilizador;
    }

    @Transactional
    public Utilizador update(int id, String nome, String password, String email, int numTele, String local, String tipo) {
        return utilizadorRepository.update(id, nome, password, email, numTele, local, tipo);
    }

    @Transactional
    public boolean delete(int id) {
        return utilizadorRepository.delete(id);
    }

    public Utilizador login(String email, String password) {
        Utilizador utilizador = utilizadorRepository.getByEmail(email);

        if (utilizador == null) {
            return null;
        }

        if (!utilizador.getPassword().equals(password)) {
            return null;
        }

        return utilizador;
    }
}
