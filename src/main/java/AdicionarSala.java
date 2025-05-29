import javax.swing.*;

public class AdicionarSala {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public AdicionarSala(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
