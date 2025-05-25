import javax.swing.*;

public class Bilheteira {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public Bilheteira(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
