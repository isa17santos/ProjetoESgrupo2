import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseDadosTestDalot {
    @org.junit.jupiter.api.Test
    void adicionarProduto() {

        BaseDados baseDados = BaseDados.getInstance();

        int id = baseDados.getLastIdProduto();
        Produto produto = new Produto(id + 1,"cocaCola.png","Coca Cola 2",TipoProduto.BEBIDA,Estado.ATIVO,20,0.50f,1.50f);

        baseDados.adicionarProduto(produto);

        //obtém a lista de produtos de base de dados
        List<Produto> produtos = baseDados.getProdutos();

        assertTrue(produtos.contains(produto));
    }

    @org.junit.jupiter.api.Test
    void getProdutos() {
        BaseDados baseDados = BaseDados.getInstance();

        //obtém a lista de produtos de base de dados
        List<Produto> produtos = baseDados.getProdutos();

        //verifica se não retorna uma lista null
        assertNotNull(produtos);

        Produto produto1 = new Produto(1,"cocaCola.png","Coca Cola",TipoProduto.BEBIDA,Estado.ATIVO,20,0.50f,1.50f);
        Produto produto2 = new Produto(2,"sumolAnanas.png","Sumol Ananás",TipoProduto.BEBIDA,Estado.ATIVO,50,0.50f,1.50f);

        System.out.println(produtos.toString());
        assertTrue(produtos.contains(produto1));
        assertTrue(produtos.contains(produto2));

    }

    @org.junit.jupiter.api.Test
    void getProdutosPorTipo() {

        BaseDados baseDados = BaseDados.getInstance();

        //obtém a lista de produtos de base de dados
        List<Produto> produtos = baseDados.getProdutos();

        List<Produto> bebidas =  baseDados.getProdutosPorTipo(TipoProduto.BEBIDA);

        for (Produto produto : bebidas) {
            assertEquals(TipoProduto.BEBIDA, produto.getTipoProduto(), "Encontrado um produto que não é uma bedida");
        }
    }

    @org.junit.jupiter.api.Test
    void adicionarAoCarrinho() {

        BaseDados baseDados = BaseDados.getInstance();
        List<ObjetoCarrinho> carrinho = baseDados.getElementosCarrinho();
        List<Produto> produtos = baseDados.getProdutos();

        //verifica se não retorna uma lista null
        assertNotNull(carrinho);

        assertEquals(0, carrinho.size(), "O carrinho não está vazio");

        Produto produto = produtos.get(1);

        ObjetoCarrinho objetoCarrinho = new ObjetoCarrinho(produto, 10);

        baseDados.adicionarAoCarrinho(objetoCarrinho);

        carrinho = baseDados.getElementosCarrinho();
        assertEquals(1, carrinho.size(), "O produto não foi adicionado ao carrinho");

        assertTrue(carrinho.contains(objetoCarrinho));

    }

    @org.junit.jupiter.api.Test
    void pagamentoCarrinho() {

        BaseDados baseDados = BaseDados.getInstance();
        List<Produto> produtos = baseDados.getProdutos();

        //tem 20 em stock
        Produto produto = produtos.get(0);

        // --- verifica se o stock não está baixo
        ObjetoCarrinho objetoCarrinho = new ObjetoCarrinho(produto, 10);

        baseDados.adicionarAoCarrinho(objetoCarrinho);

        boolean stockBaixo = baseDados.pagamentoCarrinho();

        //carinho
        List<ObjetoCarrinho> carrinho = baseDados.getElementosCarrinho();

        assertEquals(0, carrinho.size(), "Esperado um carrinho vazio");
        assertNotNull(carrinho);
        assertFalse(stockBaixo);
    }

}
