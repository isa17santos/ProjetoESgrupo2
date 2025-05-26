import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bar {
    private JPanel mainPanel;
    private final AppWindow app;

    public Bar(AppWindow app) {
        this.app = app;
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(253, 227, 167));
        mainPanel.setLayout(null);

        // Logo
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));
        logoLabel.setBounds(30, 20, 160, 160);
        mainPanel.add(logoLabel);

        // Seta voltar atrás
        JLabel voltarLabel = new JLabel();
        ImageIcon setaIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image setaImg = setaIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        voltarLabel.setIcon(new ImageIcon(setaImg));
        voltarLabel.setBounds(50, 190, 50, 50);
        voltarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltarLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaInicial();
            }
        });
        mainPanel.add(voltarLabel);

        // Título "Bar"
        JLabel tituloLabel = new JLabel("Bar");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 50));
        tituloLabel.setForeground(new Color(255, 153, 0));
        tituloLabel.setBounds(575, 50, 200, 60);
        mainPanel.add(tituloLabel);
// ---------- Botões estilizados como no Admin ----------

        int buttonWidth = 230;
        int buttonHeight = 90;
        int gap = 50;

// Calcula posição para centralizar dois botões lado a lado
        int totalWidth = 2 * buttonWidth + gap;
        int startX = (1300 - totalWidth) / 2;

// Bebidas
        JButton btnBebidas = new JButton("Bebidas");
        btnBebidas.setFont(new Font("Arial", Font.BOLD, 24));
        btnBebidas.setBackground(new Color(255, 189, 68));
        btnBebidas.setFocusPainted(false);
        btnBebidas.setBounds(startX, 250, buttonWidth, buttonHeight);
        btnBebidas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBebidas.setBorder(BorderFactory.createEmptyBorder());
        //btnBebidas.addActionListener(e -> app.mostrarPaginaBebidas());
        mainPanel.add(btnBebidas);

// Aperitivos
        JButton btnAperitivos = new JButton("Aperitivos");
        btnAperitivos.setFont(new Font("Arial", Font.BOLD, 24));
        btnAperitivos.setBackground(new Color(255, 189, 68));
        btnAperitivos.setFocusPainted(false);
        btnAperitivos.setBounds(startX + buttonWidth + gap, 250, buttonWidth, buttonHeight);
        btnAperitivos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAperitivos.setBorder(BorderFactory.createEmptyBorder());
       // btnAperitivos.addActionListener(e -> app.mostrarPaginaAperitivos());
        mainPanel.add(btnAperitivos);

// Packs (na linha de baixo, centralizado)
        JButton btnPacks = new JButton("Packs");
        btnPacks.setFont(new Font("Arial", Font.BOLD, 24));
        btnPacks.setBackground(new Color(255, 189, 68));
        btnPacks.setFocusPainted(false);
        btnPacks.setBounds((1300 - buttonWidth) / 2, 360, buttonWidth, buttonHeight);
        btnPacks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPacks.setBorder(BorderFactory.createEmptyBorder());
        //btnPacks.addActionListener(e -> app.mostrarPaginaPacks());
        mainPanel.add(btnPacks);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
