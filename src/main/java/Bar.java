import javax.swing.*;

public class Bar {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public Bar(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
