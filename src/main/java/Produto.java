import java.io.Serializable;
import java.util.Objects;

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
        if (quantidadeVendida > stock) {
            throw new IllegalArgumentException("Quantidade vendida excede o stock disponível.");
        }else {
            this.quantidadeVendida = quantidadeVendida;
            this.stock -= quantidadeVendida;
        }

    }

    public void venderProduto(int quantidade) {
        if (quantidade > stock) {
            throw new IllegalArgumentException("Quantidade vendida excede o stock disponível.");
        }else {
            quantidadeVendida += quantidade;
            this.stock -= quantidadeVendida;
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
                ", quantidadeVendida=" + quantidadeVendida +
                ", precoCompraUnidade=" + precoCompraUnidade +
                ", precoVendaUnidade=" + precoVendaUnidade +
                ", precoTotalEmStock=" + precoTotalEmStock +
                ", precoTotalComprado=" + precoTotalComprado +
                ", idProduto=" + idProduto +
                '}';
    }

    // --- funções para poder fazer a comparação correta nos testes unitários ---
    //compara o conteúdo
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Produto produto = (Produto) object;
        //retirado o stock e quantidade vendida,precoTotalEmStock e precoTotalComprado uma vez que são números random
        return  Float.compare(precoCompraUnidade, produto.precoCompraUnidade) == 0 && Float.compare(precoVendaUnidade, produto.precoVendaUnidade) == 0 && idProduto == produto.idProduto && Objects.equals(foto, produto.foto) && Objects.equals(nome, produto.nome) && tipoProduto == produto.tipoProduto && estado == produto.estado;
    }

    @Override
    public int hashCode() {
        //retirado o stock e quantidade vendida,precoTotalEmStock e precoTotalComprado uma vez que são números rando
        return Objects.hash(foto, nome, tipoProduto, estado, precoCompraUnidade, precoVendaUnidade, idProduto);
    }

    // --- funções para poder fazer a comparação correta nos testes unitários ---

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
