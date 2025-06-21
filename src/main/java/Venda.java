import java.io.Serializable;

public class Venda implements Serializable {
    private ObjetoCarrinho itemVendido; // Produto ou Bilhete
    private int quantidade;     // Para Produto, 1 para Bilhete
    private float precoUnitario;
    private float precoTotal;
    private String tipo;        // "Produto" ou "Bilhete"
    private String descricao;   // Nome do produto ou info do bilhete
    private java.time.LocalDateTime dataHora;

    public Venda(ObjetoCarrinho itemVendido, int quantidade, float precoUnitario, String tipo, String descricao) {
        this.itemVendido = itemVendido;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.precoTotal = precoUnitario * quantidade;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataHora = java.time.LocalDateTime.now();
    }

    public Object getItemVendido() {
        return itemVendido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public java.time.LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", precoTotal=" + precoTotal +
                ", dataHora=" + dataHora +
                '}';
    }
}