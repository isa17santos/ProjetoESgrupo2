import javax.swing.*;
import java.awt.*;

public class EditarSala {
    private JPanel mainPanel;
    private final AppWindow app;

    //construtor
    public EditarSala(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
