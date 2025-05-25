import javax.swing.*;

public class Carrinho {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public Carrinho(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
