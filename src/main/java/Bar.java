import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.miginfocom.swing.MigLayout;

public class Bar {
    private JPanel mainPanel;
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel barLabel = new JLabel("Bar");
    private JButton bebidasButton;
    private JButton aperitivosButton;
    private JButton packsButton;

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
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));


        // seta andar para atras
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));

        // --------------------- BAR LABEL -----------------------
        barLabel.setHorizontalAlignment(SwingConstants.CENTER);
        barLabel.setForeground(corFundoLabel);
        barLabel.setBackground(corFundo);
        barLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        barLabel.setOpaque(true);
        // --------------------- BAR LABEL -----------------------

        //----------------- BOTAO BEBIDAS -------------
        bebidasButton = new RoundedButton("Bebidas", 20);
        bebidasButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        bebidasButton.setBackground(corFundoComponentes);
        bebidasButton.setForeground(corFonte); // texto
        //----------------- BOTAO BEBIDAS -------------

        //----------------- BOTAO APERITIVOS -------------
        aperitivosButton = new RoundedButton("Aperitivos", 20);
        aperitivosButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        aperitivosButton.setBackground(corFundoComponentes);
        aperitivosButton.setForeground(corFonte); // texto
        //----------------- BOTAO APERITIVOS -------------

        //----------------- BOTAO PACKS -------------
        packsButton = new RoundedButton("Packs", 20);
        packsButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        packsButton.setBackground(corFundoComponentes);
        packsButton.setForeground(corFonte); // texto
        //----------------- BOTAO PACKS -------------


        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(barLabel, "x 590, y 30");
        mainPanel.add(bebidasButton, "x 170, y 350, w 400, h 100");
        mainPanel.add(aperitivosButton, "x 720, y 350, w 400, h 100");
        mainPanel.add(packsButton, "x 450, y 550, w 400, h 100");

        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaInicial();
            }
        });

        bebidasButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bebidasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaBebidas();
            }
        });

        aperitivosButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aperitivosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaAperitivos();
            }
        });


        packsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        packsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaPacks();
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
