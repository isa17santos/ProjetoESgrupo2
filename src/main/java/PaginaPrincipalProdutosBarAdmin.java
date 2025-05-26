import javax.swing.*;

public class PaginaPrincipalProdutosBarAdmin {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public PaginaPrincipalProdutosBarAdmin(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
