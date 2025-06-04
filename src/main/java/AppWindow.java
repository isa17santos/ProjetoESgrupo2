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


    public void mostrarPaginaAperitivos() {
        PaginaAperitivos pagina = new PaginaAperitivos(this);
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

    public void mostrarCarrinho() {
        Carrinho carrinho = new Carrinho(this);
        trocarPainel(carrinho.getMainPanel());
    }

    public void mostrarBilheteira() {
        Bilheteira bilheteira = new Bilheteira(this);
        trocarPainel(bilheteira.getMainPanel());
    }

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
    public void mostrarPaginaPrincipalSessoesAdmin() {
        Criarsessao criarSessao = new Criarsessao(this);
        setContentPane(criarSessao.getMainPanel());
        revalidate();
        repaint();
    }
// --------------------------------------- MODO ADMIN --------------------------------------------------------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppWindow::new);
    }


}
