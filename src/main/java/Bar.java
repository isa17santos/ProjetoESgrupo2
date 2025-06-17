import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;

public class Bar {
    private JPanel mainPanel;
    private JLabel logoLabel;
    private JLabel voltaLabel;
    private JLabel titleLabel;
    private final AppWindow app;
    private JButton aperitivosButton;
    private JButton bebidasButton;
    private JButton packsButton;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    // Construtor
    public Bar(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {
        // Inicializar componentes
        mainPanel = new JPanel();
        logoLabel = new JLabel();
        voltaLabel = new JLabel();
        titleLabel = new JLabel();

        // Layout e fundo
        mainPanel.setLayout(new MigLayout("nogrid, insets 0"));
        mainPanel.setBackground(corFundo);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));

        // Seta voltar
        ImageIcon setaIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image setaImg = setaIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(setaImg));
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                app.mostrarPaginaInicial();
            }
        });

        // Título
        titleLabel.setText("Bar");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(corFundoLabel);
        titleLabel.setBackground(corFundo);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        titleLabel.setOpaque(true);

        //Botoes
        bebidasButton = new RoundedButton("Bebidas", 20);
        bebidasButton.setFont(new Font("Georgia", Font.PLAIN, 50));
        bebidasButton.setBackground(corFundoComponentes);
        bebidasButton.setForeground(corFonte);
        bebidasButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bebidasButton.addActionListener(e -> app.mostrarPaginaBebidas());

        aperitivosButton = new RoundedButton("Aperitivos", 20);
        aperitivosButton.setFont(new Font("Georgia", Font.PLAIN, 50));
        aperitivosButton.setBackground(corFundoComponentes);
        aperitivosButton.setForeground(corFonte);
        aperitivosButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aperitivosButton.addActionListener(e -> app.mostrarPaginaAperitivos());

        packsButton = new RoundedButton("Packs", 20);
        packsButton.setFont(new Font("Georgia", Font.PLAIN, 50));
        packsButton.setBackground(corFundoComponentes);
        packsButton.setForeground(corFonte);
        packsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        packsButton.addActionListener(e -> app.mostrarPaginaPacks());

        // Adicionar ao painel
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 50, y 220, w 100, h 100");
        mainPanel.add(titleLabel, "x 400, y 23, w 500, h 100");
        mainPanel.add(bebidasButton, "x 150, y 350, w 400, h 140");
        mainPanel.add(aperitivosButton, "x 800, y 350, w 400, h 140");
        mainPanel.add(packsButton, "x 450, y 550, w 400, h 140");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
