import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import net.miginfocom.swing.MigLayout;

public class PaginaInicial {
    private JPanel mainPanel;
    private JButton bilheteiraButton;
    private JButton barButton;
    private JButton estatisticasButton;
    private JButton adminButton;
    private JLabel logoLabel;
    private JLabel cartLabel;
    private final AppWindow app; // referência à janela principal

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    //construtor
    public PaginaInicial(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {

        // pagina principal
        mainPanel.setLayout(new net.miginfocom.swing.MigLayout("nogrid, insets 0"));
        mainPanel.setBackground(corFundo);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logoo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(600, 280, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));


        // Carrinho
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/carrinho_sem_compras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        cartLabel.setIcon(new ImageIcon(cartImg));

        //----------------- BOTAO BILHETEIRA -------------
        bilheteiraButton = new RoundedButton("Bilheteira", 20);
        bilheteiraButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        bilheteiraButton.setBackground(corFundoComponentes);
        bilheteiraButton.setForeground(corFonte); // texto
        //----------------- BOTAO BILHETEIRA -------------

        //----------------- BOTAO BAR -------------
        barButton = new RoundedButton("Bar", 20);
        barButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        barButton.setBackground(corFundoComponentes);
        barButton.setForeground(corFonte); // texto
        //----------------- BOTAO BAR -------------

        //----------------- BOTAO ESTATISTICAS -------------
        estatisticasButton = new RoundedButton("Estatisticas", 20);
        estatisticasButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        estatisticasButton.setBackground(corFundoComponentes);
        estatisticasButton.setForeground(corFonte); // texto
        //----------------- BOTAO ESTATISTICAS -------------

        //----------------- BOTAO ADMIN -------------
        adminButton = new RoundedButton("Admin", 20);
        adminButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        adminButton.setBackground(corFundoComponentes);
        adminButton.setForeground(corFonte); // texto
        //----------------- BOTAO ADMIN -------------

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 350, y 30");
        mainPanel.add(cartLabel, "x 1100, y 200");
        mainPanel.add(bilheteiraButton, "x 150, y 350, w 400, h 100");
        mainPanel.add(barButton, "x 700, y 350, w 400, h 100");
        mainPanel.add(estatisticasButton, "x 150, y 500, w 400, h 100");
        mainPanel.add(adminButton, "x 700, y 500, w 400, h 100");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Carrinho
        cartLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cartLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                app.mostrarCarrinho();
            }
        });

        // Redirecionar para Bilheteira
        bilheteiraButton.addActionListener(e -> app.mostrarBilheteira());

        // Redirecionar para Bar
        barButton.addActionListener(e -> app.mostrarBar());

        // Redirecionar para Estatísticas
        estatisticasButton.addActionListener(e -> app.mostrarEstatisticas());

        // Redirecionar para Admin
        adminButton.addActionListener(e -> app.mostrarAdmin());

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
