package marketplace.model;

import java.util.List;

// DTO used to send only the order information needed by the frontend.
public class EncomendaDTO {

    private int id;
    private String estado;
    private float preco_total;
    private List<LinhaEncomendaDTO> linhas;

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

    public List<LinhaEncomendaDTO> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<LinhaEncomendaDTO> linhas) {
        this.linhas = linhas;
    }
}