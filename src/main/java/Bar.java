import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.miginfocom.swing.MigLayout;

public class Bar {
    private JPanel mainPanel;
    private final AppWindow app;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    public Bar(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {
        mainPanel = new JPanel();
        mainPanel.setBackground(corFundo);
        mainPanel.setLayout(new MigLayout("nogrid, insets 0"));

        // Logo
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));

        // Seta voltar
        JLabel voltarLabel = new JLabel();
        ImageIcon setaIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image setaImg = setaIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        voltarLabel.setIcon(new ImageIcon(setaImg));
        voltarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltarLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaInicial();
            }
        });

        // Título
        JLabel tituloLabel = new JLabel("Bar");
        tituloLabel.setFont(new Font("Georgia", Font.PLAIN, 70));
        tituloLabel.setForeground(corFonte);
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloLabel.setForeground(corFundoLabel);
        tituloLabel.setBackground(corFundo);
        tituloLabel.setOpaque(true);

        // Botões
        JButton btnBebidas = new RoundedButton("Bebidas", 20);
        btnBebidas.setFont(new Font("Georgia", Font.PLAIN, 35));
        btnBebidas.setBackground(corFundoComponentes);
        btnBebidas.setForeground(corFonte);
        btnBebidas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBebidas.addActionListener(e -> app.mostrarPaginaBebidas());

        JButton btnAperitivos = new RoundedButton("Aperitivos", 20);
        btnAperitivos.setFont(new Font("Georgia", Font.PLAIN, 35));
        btnAperitivos.setBackground(corFundoComponentes);
        btnAperitivos.setForeground(corFonte);
        btnAperitivos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //btnAperitivos.addActionListener(e -> app.mostrarPaginaAperitivos());

        JButton btnPacks = new RoundedButton("Packs", 20);
        btnPacks.setFont(new Font("Georgia", Font.PLAIN, 35));
        btnPacks.setBackground(corFundoComponentes);
        btnPacks.setForeground(corFonte);
        btnPacks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPacks.addActionListener(e -> app.mostrarPaginaPacks());

        // Adiciona componentes com MigLayout (coordenadas ajustáveis)
        mainPanel.add(logoLabel, "x 30, y 20, w 160, h 160");
        mainPanel.add(voltarLabel, "x 50, y 200, w 50, h 50");
        mainPanel.add(tituloLabel, "x 575, y 50");

        // Botões centralizados
        mainPanel.add(btnBebidas, "x 250, y 250, w 300, h 100");
        mainPanel.add(btnAperitivos, "x 700, y 250, w 300, h 100");
        mainPanel.add(btnPacks, "x 475, y 400, w 300, h 100");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
