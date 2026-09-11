package marketplace.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String estado;
    private int preco_total;

    @ManyToOne 
    @JoinColumn(name = "id_comprador", nullable = false)
    private Utilizador comprador;

    public Encomenda() {
    }

    public Encomenda(String estado, int preco_total, Utilizador comprador) {
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

    public int getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(int preco_total) {
        this.preco_total = preco_total;
    }

    public Utilizador getComprador() {
        return comprador;
    }

    public void setComprador(Utilizador comprador) {
        this.comprador = comprador;
    }   
}
