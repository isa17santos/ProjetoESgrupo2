import javax.swing.*;

public class PaginaPrincipalSalasAdmin {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public PaginaPrincipalSalasAdmin(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
