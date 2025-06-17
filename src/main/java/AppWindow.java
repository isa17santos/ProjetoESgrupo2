import javax.swing.*;

public class AppWindow extends JFrame {
    public AppWindow() {
        setTitle("Cinemagic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 780);
        setResizable(false);
        setLocationRelativeTo(null);
        mostrarPaginaInicial(); // Página inicial ao arrancar
        setVisible(true);
    }

    public void trocarPainel(JPanel novoPainel) {
        setContentPane(novoPainel);
        revalidate();
        repaint();
    }
    public void mostrarPaginaBebidas() {
        PaginaBebidas pagina = new PaginaBebidas(this);
        trocarPainel(pagina.getMainPanel());
    }


    public void mostrarPaginaPacks() {
        PaginaPacks pagina = new PaginaPacks(this);
        trocarPainel(pagina.getMainPanel());
    }

    public void mostrarPaginaInicial() {
        PaginaInicial paginaInicial = new PaginaInicial(this);
        trocarPainel(paginaInicial.getMainPanel());
    }

    /// carrinho
    public void mostrarCarrinho() {
        Carrinho carrinho = new Carrinho(this);
        trocarPainel(carrinho.getMainPanel());
    }

    public void mostrarCarrinhoPagamento(float total) {
        CarrinhoPagamento pagamento = new CarrinhoPagamento(this, total);
        trocarPainel(pagamento.getMainPanel());
    }

    public void mostrarConfirmacaoCarrinhoPagamento() {
        ConfirmacaoCarrinhoPagamento pagamento = new ConfirmacaoCarrinhoPagamento(this);
        trocarPainel(pagamento.getMainPanel());
    }



    //-------------------------- BILHETEIRA -----------------------------
    public void mostrarBilheteira() {
        Bilheteira bilheteira = new Bilheteira(this);
        trocarPainel(bilheteira.getMainPanel());
    }

    //escolha filme bilheteira
    public void mostrarEscolhaFilmeBilhteira(Filme filme) {
        EscolhaFilmeBilheteira filmes = new EscolhaFilmeBilheteira(this, filme);
        trocarPainel(filmes.getMainPanel());
    }


    //escolha sessao bilheteira
    public void mostrarPaginaSessoes(Filme filme) {
        PaginaEscolhaSessao filmes = new PaginaEscolhaSessao(this, filme);
        trocarPainel(filmes.getMainPanel());
    }

    //escolha sessao com sala especifica bilheteira
    public void mostrarPaginaSessoesSalaEscolhida(Filme filme, String sala) {
        PaginaEscolhaSessaoComSala filmes = new PaginaEscolhaSessaoComSala(this, filme, sala);
        trocarPainel(filmes.getMainPanel());
    }

    //escolha lugar bilheteira
    public void mostrarEscolherLugar(Sessao sessao, boolean comSala) {
        EscolherLugar filmes = new EscolherLugar(this, sessao, comSala);
        trocarPainel(filmes.getMainPanel());
    }

    //escolha lugar sala vip bilheteira
    public void mostrarEscolherLugarSalaVip(Sessao sessao) {
        EscolherLugarSalaVip filmes = new EscolherLugarSalaVip(this, sessao);
        trocarPainel(filmes.getMainPanel());
    }

    //-------------------------- BILHETEIRA -----------------------------

    public void mostrarBar() {
        Bar bar = new Bar(this);
        trocarPainel(bar.getMainPanel());
    }

    public void mostrarEstatisticas() {
        Estatisticas est = new Estatisticas(this);
        trocarPainel(est.getMainPanel());
    }

// --------------------------------------- MODO ADMIN --------------------------------------------------------
    public void mostrarAdmin() {
        AdminPaginaPrincipal admin = new AdminPaginaPrincipal(this);
        trocarPainel(admin.getMainPanel());
    }




    //-------------------- FILMES ------------------
    //pagina principal
    public void mostrarPaginaPrincipalFilmesAdmin() {
        PaginaPrincipalFilmesAdmin filmes = new PaginaPrincipalFilmesAdmin(this);
        trocarPainel(filmes.getMainPanel());
    }

    //adicionar filme
    public void mostrarAdicionarFilmes() {
        AdicionarFilme filmes = new AdicionarFilme(this);
        trocarPainel(filmes.getMainPanel());
    }

    //confirmar adicao filme
    public void mostrarConfirmacaoAdicaoFilmes() {
        ConfirmacaoAdicaoFilme filmes = new ConfirmacaoAdicaoFilme(this);
        trocarPainel(filmes.getMainPanel());
    }

    //escolher filme a editar
    public void mostrarEscolhaFilmeEditar() {
        PaginaEscolhaFilmeEditar filmes = new PaginaEscolhaFilmeEditar(this);
        trocarPainel(filmes.getMainPanel());
    }

    //editar filme
    public void mostrarEditarFilmes(Filme filme) {
        EditarFilme filmes = new EditarFilme(this, filme);
        trocarPainel(filmes.getMainPanel());
    }

    //confirmar edicao filme
    public void mostrarConfirmacaoEdicaoFilmes() {
        ConfirmacaoEdicaoFilme filmes = new ConfirmacaoEdicaoFilme(this);
        trocarPainel(filmes.getMainPanel());
    }

    //-------------------- FILMES -------------------





    //-------------------- SALAS ------------------

    //pagina principal
    public void mostrarPaginaPrincipalSalasAdmin() {
        PaginaPrincipalSalasAdmin salas = new PaginaPrincipalSalasAdmin(this);
        trocarPainel(salas.getMainPanel());
    }

    //adicionar sala
    public void mostrarAdicionarSalas() {
        AdicionarSala salas = new AdicionarSala(this);
        trocarPainel(salas.getMainPanel());
    }

    // confirmar criação sala
    public void mostrarConfirmarCriacaoSala(String nomeSala) {
        ConfirmarCriacaoSala confirmacao = new ConfirmarCriacaoSala(this, nomeSala);
        trocarPainel(confirmacao.getMainPanel());
    }

    // editar sala seleção
    public void mostrarEditarSalaSelecao() {
        EditarSalaSelecao salas = new EditarSalaSelecao(this);
        trocarPainel(salas.getMainPanel());
    }

    // editar sala
    public void mostrarEditarSala(String nomeSala) {
        EditarSala salas = new EditarSala(this, nomeSala);
        trocarPainel(salas.getMainPanel());
    }

    // confirmar edição sala
    public void mostrarConfirmarEdicaoSala(String nomeSala) {
        ConfirmarEdicaoSala confirmacao = new ConfirmarEdicaoSala(this, nomeSala);
        trocarPainel(confirmacao.getMainPanel());
    }
    //-------------------- SALAS ------------------





    public void mostrarPaginaPrincipalProdutosBarAdmin() {
        PaginaPrincipalProdutosBarAdmin produtos = new PaginaPrincipalProdutosBarAdmin(this);
        trocarPainel(produtos.getMainPanel());
    }

    //-------------------- SESSÃO ------------------
    //pagina principal
    public void mostrarPaginaPrincipalSessoesAdmin() {
        PaginaPrincipalSessoesAdmin Sessao = new PaginaPrincipalSessoesAdmin(this);
        trocarPainel(Sessao.getMainPanel());
    }
    //adicionar sessao
    public void mostrarAdicionarSessao() {
        CriarSessao sessao = new CriarSessao(this);
        trocarPainel(sessao.getMainPanel());
    }
    // editar sessao
    public void mostrarEditarSessao(String nomeSessao) {
        EditarSessao sessao = new EditarSessao(this, nomeSessao);
        trocarPainel(sessao.getMainPanel());
    }
    // editar sala seleção
    public void mostrarEditarSessaoAI() {
        EditarSessaoAI1 sessao = new EditarSessaoAI1(this);
        trocarPainel(sessao.getMainPanel());
    }
    //-------------------- FIM SESSÃO ------------------

    public void mostrarPaginaConfirmacaoAdicaoProduto() {
        ConfirmacaoAdicaoProduto confirmacaoAdicaoProduto = new ConfirmacaoAdicaoProduto(this);
        trocarPainel(confirmacaoAdicaoProduto.getMainPanel());
    }

    //-------------------- BAR ------------------
    //adicionar paginabar
    public void mostrarBarAdmin () {
        BarAdmin bar = new BarAdmin(this);
        trocarPainel(bar.getMainPanel());
    }
    //-------------------- FIM BAR ------------------

    public void mostrarPaginaAdicionarProduto () {
        AdicionarProdutoAdmin adicionarProdutoAdmin = new AdicionarProdutoAdmin(this);
        trocarPainel(adicionarProdutoAdmin.getMainPanel());
    }

    // --- edicao produto ---
    public void mostrarPaginaEscolherProdutoEditar () {
        PaginaEscolherProdutoEditar paginaEscolherProdutoEditar = new PaginaEscolherProdutoEditar(this);
        trocarPainel(paginaEscolherProdutoEditar.getMainPanel());
    }

    public void mostrarPaginaEditarProduto (Produto produto){
        EditarProduto editarProduto = new EditarProduto(this, produto);
        trocarPainel(editarProduto.getMainPanel());
    }

    public void mostarConfirmacaoEditarProduto () {
        ConfirmacaoEdicaoProduto confirmacaoEdicaoProduto = new ConfirmacaoEdicaoProduto(this);
        trocarPainel(confirmacaoEdicaoProduto.getMainPanel());

    }

// --------------------------------------- MODO ADMIN --------------------------------------------------------

    public static void main (String[]args){
        SwingUtilities.invokeLater(AppWindow::new);
    }
}
