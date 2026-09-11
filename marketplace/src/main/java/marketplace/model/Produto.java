package marketplace.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity 
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private float preco;
    private String categoria;
    private int stock;

    @OneToOne 
    @JoinColumn(name = "id_vendedor")
    private Utilizador vendedor;

    public Produto() {
    }

    public Produto(String nome, float preco, String categoria, int stock, Utilizador vendedor) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.stock = stock;
        this.vendedor = vendedor;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public Utilizador getVendedor() {
        return vendedor;
    }
    public void setVendedor(Utilizador vendedor) {
        this.vendedor = vendedor;
    }
}
