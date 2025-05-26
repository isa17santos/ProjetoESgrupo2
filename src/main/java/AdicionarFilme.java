import javax.swing.*;

public class AdicionarFilme {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public AdicionarFilme(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
