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

    //editar filme
    public void mostrarEditarFilmes() {
        EditarFilme filmes = new EditarFilme(this);
        trocarPainel(filmes.getMainPanel());
    }
    //-------------------- FILMES -------------------

    public void mostrarPaginaPrincipalProdutosBarAdmin() {
        PaginaPrincipalProdutosBarAdmin produtos = new PaginaPrincipalProdutosBarAdmin(this);
        trocarPainel(produtos.getMainPanel());
    }

    public void mostrarPaginaPrincipalSalasAdmin() {
        PaginaPrincipalSalasAdmin salas = new PaginaPrincipalSalasAdmin(this);
        trocarPainel(salas.getMainPanel());
    }

    public void mostrarPaginaPrincipalSessoesAdmin() {
        PaginaPrincipalSessoesAdmin sessoes = new PaginaPrincipalSessoesAdmin(this);
        trocarPainel(sessoes.getMainPanel());
    }
// --------------------------------------- MODO ADMIN --------------------------------------------------------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppWindow::new);
    }
}
