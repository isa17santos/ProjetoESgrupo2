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
    private final Color corBotaoSetaComboBox = Color.decode("#F2AF14");
    private final Color corHoverComboBox = Color.decode("#FCD373");
    private final Color corFundoSubMenu = Color.decode("#FBDC95");
    private final Color corFundo = Color.decode("#F9E6BB");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    //construtor
    public PaginaInicial(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {
        Border rounded = new RoundedBorder(15); // define as bordas redondas

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
        bilheteiraButton.setText("Bilheteira");
        bilheteiraButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        bilheteiraButton.setHorizontalAlignment(SwingConstants.CENTER);
        bilheteiraButton.setBackground(corFundoComponentes);
        bilheteiraButton.setBorder(rounded);
        bilheteiraButton.setContentAreaFilled(false);
        bilheteiraButton.setOpaque(true);
        //----------------- BOTAO BILHETEIRA -------------

        //----------------- BOTAO BAR -------------
        barButton.setText("Bar");
        barButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        barButton.setHorizontalAlignment(SwingConstants.CENTER);
        barButton.setBackground(corFundoComponentes);
        barButton.setBorder(rounded);
        barButton.setContentAreaFilled(false);
        barButton.setOpaque(true);
        //----------------- BOTAO BAR -------------

        //----------------- BOTAO ESTATISTICAS -------------
        estatisticasButton.setText("Estatisticas");
        estatisticasButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        estatisticasButton.setHorizontalAlignment(SwingConstants.CENTER);
        estatisticasButton.setBackground(corFundoComponentes);
        estatisticasButton.setBorder(rounded);
        estatisticasButton.setContentAreaFilled(false);
        estatisticasButton.setOpaque(true);
        //----------------- BOTAO ESTATISTICAS -------------

        //----------------- BOTAO ADMIN -------------
        adminButton.setText("Admin");
        adminButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        adminButton.setHorizontalAlignment(SwingConstants.CENTER);
        adminButton.setBackground(corFundoComponentes);
        adminButton.setBorder(rounded);
        adminButton.setContentAreaFilled(false);
        adminButton.setOpaque(true);
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


    static class RoundedBorder implements Border {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY); // Podes mudar a cor da borda aqui
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }

    static class RoundedPanel extends JPanel {
        private final int radius;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
