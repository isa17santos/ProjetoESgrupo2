public class Produto {
    private String foto;
    private String nome;
    private TipoProduto tipoProduto;
    private Estado estado;
    private int stock;
    private float precoCompraUnidade;
    private float precoVendaUnidade;

    public Produto(String foto, String nome, TipoProduto tipoProduto, Estado estado, int stock, float precoCompraUnidade, float precoVendaUnidade) {
        this.foto = foto;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.estado = estado;
        this.stock = stock;
        this.precoCompraUnidade = precoCompraUnidade;
        this.precoVendaUnidade = precoVendaUnidade;
    }

    public String getFoto() {
        return foto;
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
}
