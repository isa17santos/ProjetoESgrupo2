import javax.swing.*;

public class AdminPaginaPrincipal {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public AdminPaginaPrincipal(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
