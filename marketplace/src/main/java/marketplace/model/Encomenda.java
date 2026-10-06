package marketplace.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity 
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Pendente, Em processamento, Enviada, Entregue, Cancelada
    private String estado;
    
    private float preco_total;

    @ManyToOne 
    @JoinColumn(name = "id_comprador", nullable = false)
    private Utilizador comprador;

    @OneToMany(
        mappedBy = "encomenda",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<LinhaEncomenda> linhas = new ArrayList<>();

    public Encomenda() {
    }

    public Encomenda(String estado, float preco_total, Utilizador comprador) {
        this.estado = estado;
        this.preco_total = preco_total;
        this.comprador = comprador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(float preco_total) {
        this.preco_total = preco_total;
    }

    public Utilizador getComprador() {
        return comprador;
    }

    public void setComprador(Utilizador comprador) {
        this.comprador = comprador;
    }
    
    public List<LinhaEncomenda> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<LinhaEncomenda> linhas) {
        this.linhas = linhas;
    }
}
