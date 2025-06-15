import java.io.Serializable;
import java.util.List;

public class ObjetoCarrinho implements Serializable {

    //objeto pode ser produto ou outro ( colocar bilhete?)
    private Serializable objeto;

    //todos os objetos tem sempre quantidade e desconto
    private int quantidade;
    private float desconto;

    public ObjetoCarrinho(Serializable objeto, int quantidade){
        this.objeto = objeto;
        this.quantidade = quantidade;
        this.desconto = 0;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public float getDesconto() {
        return desconto;
    }

    @Override
    public String toString() {
        String mensagem = "";
        if(objeto instanceof Produto){
            mensagem = "Produto{" +
                    "foto='" + ((Produto) objeto).getFoto() + '\'' +
                    ", nome='" + ((Produto) objeto).getNome() + '\'' +
                    ", tipoProduto=" + ((Produto) objeto).getTipoProduto() +
                    ", estado=" + ((Produto) objeto).getEstado() +
                    ", stock=" + ((Produto) objeto).getStock() +
                    ", precoCompraUnidade=" + ((Produto) objeto).getPrecoCompraUnidade() +
                    ", precoVendaUnidade=" + ((Produto) objeto).getPrecoVendaUnidade() +
                    ", idProduto=" + ((Produto) objeto).getIdProduto() +
                    ", quantidadeCarrinho= " + this.quantidade +
                    '}';
        }

        return mensagem;


    }
}
