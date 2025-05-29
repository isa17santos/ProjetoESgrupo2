import javax.swing.*;

public class ConfirmacaoAdicaoFilme {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public ConfirmacaoAdicaoFilme(AppWindow app) {
        this.app = app;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
