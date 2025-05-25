import javax.swing.*;

public class Estatisticas {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public Estatisticas(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
