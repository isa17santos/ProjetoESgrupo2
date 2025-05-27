import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaginaBebidas {
    private JPanel mainPanel;
    private final AppWindow app;

    public PaginaBebidas(AppWindow app) {
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
        JLabel titulo = new JLabel("Bebidas");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setBounds(300, 30, 200, 50);
        mainPanel.add(titulo);

        // Bebidas
        String[] bebidas = {"Coca Cola", "Sumol Ananás", "Iced tea Limão", "Água"};
        int x = 80;
        for (String bebida : bebidas) {
            JLabel label = new JLabel(bebida, JLabel.CENTER);
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
                app.mostrarBar();
            }
        });
        mainPanel.add(voltarLabel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
