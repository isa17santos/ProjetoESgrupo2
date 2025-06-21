import java.io.Serializable;

public class Produto implements Serializable{
    private String foto;
    private String nome;
    private TipoProduto tipoProduto;
    private Estado estado;
    private int stock;
    private int quantidadeVendida;
    private float precoCompraUnidade;
    private float precoVendaUnidade;
    private float precoTotalEmStock;
    private float precoTotalComprado;
    private static final long serialVersionUID = 1L;
    private int idProduto;

    public Produto(int idProduto, String foto, String nome, TipoProduto tipoProduto, Estado estado, int stock, float precoCompraUnidade, float precoVendaUnidade) {
        this.idProduto = idProduto;
        this.foto = foto;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.estado = estado;
        this.stock = stock;
        this.precoCompraUnidade = precoCompraUnidade;
        this.precoVendaUnidade = precoVendaUnidade;
        this.precoTotalEmStock = stock * precoCompraUnidade; // Calcula o preço total em stock.
        this.precoTotalComprado += precoTotalEmStock;// Calcula o preço total comprado.
    }

    public String getFoto() {
        return "src/main/resources/imagens/produtos-bar/" + foto;
    }

    public String getNome() {
        return nome;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public Estado getEstado() {
        return estado;
    }

    public int getStock() {
        return stock;
    }

    public float getPrecoCompraUnidade() {
        return precoCompraUnidade;
    }

    public float getPrecoVendaUnidade() {
        return precoVendaUnidade;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecoCompraUnidade(float precoCompraUnidade) {
        this.precoCompraUnidade = precoCompraUnidade;
    }

    public void setPrecoVendaUnidade(float precoVendaUnidade) {
        this.precoVendaUnidade = precoVendaUnidade;
    }

    public int getIdProduto(){
        return idProduto;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public void venderProduto(int quantidade) {
        if (quantidade <= stock) {
            quantidadeVendida += quantidade;
        } else {
            throw new IllegalArgumentException("Quantidade excede o stock disponível.");
        }
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    //mostra na consola informação sobre os produtos
    @Override
    public String toString() {
        return "Produto{" +
                "foto='" + foto + '\'' +
                ", nome='" + nome + '\'' +
                ", tipoProduto=" + tipoProduto +
                ", estado=" + estado +
                ", stock=" + stock +
                ", precoCompraUnidade=" + precoCompraUnidade +
                ", precoVendaUnidade=" + precoVendaUnidade +
                ", idProduto=" + idProduto +
                '}';
    }

    public float getPrecoTotalEmStock() {
        return precoTotalEmStock;
    }

    public float getPrecoTotalComprado() {
        return precoTotalComprado;
    }

    public void setPrecoTotalComprado(float precoTotalComprado) {
        this.precoTotalComprado = precoTotalComprado;
    }
}
