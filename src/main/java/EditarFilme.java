import javax.swing.*;

public class EditarFilme {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public EditarFilme(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
