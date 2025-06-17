import javax.swing.*;

public class PaginaPrincipalSessoesAdmin {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public PaginaPrincipalSessoesAdmin(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
