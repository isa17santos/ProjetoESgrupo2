import javax.swing.*;

public class AppWindow extends JFrame {
    public AppWindow() {
        setTitle("Cinemagic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null);
        mostrarPaginaInicial(); // Página inicial ao arrancar
        setVisible(true);
    }

    public void trocarPainel(JPanel novoPainel) {
        setContentPane(novoPainel);
        revalidate();
        repaint();
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

    public void mostrarAdmin() {
        AdminPaginaPrincipal admin = new AdminPaginaPrincipal(this);
        trocarPainel(admin.getMainPanel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppWindow::new);
    }
}
