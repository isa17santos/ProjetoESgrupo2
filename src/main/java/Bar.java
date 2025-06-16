import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.miginfocom.swing.MigLayout;

public class Bar {
    private JPanel mainPanel;
    private final AppWindow app;
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Bar");


    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private final Color corFontePreto = Color.decode("#000000");
    private final Color corFundoSubMenu = Color.decode("#FBDC95");
    private final Color corBotaoSetaComboBox = Color.decode("#F2AF14");
    private final Color corHoverComboBox = Color.decode("#FCD373");
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
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));

        // Seta para voltar
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));


        // --------------------- ADMIN LABEL -----------------------
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminLabel.setForeground(corFundoLabel);
        adminLabel.setBackground(corFundo);
        adminLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        adminLabel.setOpaque(true);

        // Botões
        JButton btnBebidas = new RoundedButton("Bebidas", 20);
        btnBebidas.setFont(new Font("Georgia", Font.PLAIN, 45));
        btnBebidas.setBackground(corFundoComponentes);
        btnBebidas.setForeground(corFonte);
        btnBebidas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBebidas.addActionListener(e -> app.mostrarPaginaBebidas());

        JButton btnAperitivos = new RoundedButton("Aperitivos", 20);
        btnAperitivos.setFont(new Font("Georgia", Font.PLAIN, 45));
        btnAperitivos.setBackground(corFundoComponentes);
        btnAperitivos.setForeground(corFonte);
        btnAperitivos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //btnAperitivos.addActionListener(e -> app.mostrarPaginaAperitivos());

        JButton btnPacks = new RoundedButton("Packs", 20);
        btnPacks.setFont(new Font("Georgia", Font.PLAIN, 45));
        btnPacks.setBackground(corFundoComponentes);
        btnPacks.setForeground(corFonte);
        btnPacks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPacks.addActionListener(e -> app.mostrarPaginaPacks());

        // Adiciona componentes com MigLayout (coordenadas ajustáveis)
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 575, y 50");

        // Botões centralizados
        mainPanel.add(btnBebidas, "x 100, y 310, w 450, h 125");
        mainPanel.add(btnAperitivos, "x 750, y 310, w 450, h 125");
        mainPanel.add(btnPacks, "x 400, y 510, w 450, h 125");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                app.mostrarPaginaInicial();
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
