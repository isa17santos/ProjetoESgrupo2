import javax.swing.*;

public class PaginaPrincipalFilmesAdmin {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public PaginaPrincipalFilmesAdmin(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
