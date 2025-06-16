import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaginaAperitivos {
    private JPanel mainPanel;
    private final AppWindow app;

    public PaginaAperitivos(AppWindow app) {
        this.app = app;
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(253, 227, 167));

        // Logo
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));
        logoLabel.setBounds(30, 20, 100, 100);
        mainPanel.add(logoLabel);

        // Título
        JLabel titulo = new JLabel("Aperitivos");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setBounds(300, 30, 250, 50);
        mainPanel.add(titulo);

        // Aperitivos
        String[] aperitivos = {"Balde pequeno", "Balde médio", "Balde grande", "Nachos"};
        int x = 80;
        for (String snack : aperitivos) {
            JLabel label = new JLabel(snack, JLabel.CENTER);
            label.setBounds(x, 150, 100, 20);
            mainPanel.add(label);

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
            spinner.setBounds(x, 180, 50, 25);
            mainPanel.add(spinner);

            JButton adicionar = new JButton("Adicionar");
            adicionar.setBounds(x - 10, 220, 100, 25);
            mainPanel.add(adicionar);

            x += 150;
        }

        // Seta voltar
        JLabel voltarLabel = new JLabel();
        ImageIcon setaIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image setaImg = setaIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        voltarLabel.setIcon(new ImageIcon(setaImg));
        voltarLabel.setBounds(20, 250, 50, 50);
        voltarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltarLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                app.mostrarBarAdmin();
            }
        });
        mainPanel.add(voltarLabel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
