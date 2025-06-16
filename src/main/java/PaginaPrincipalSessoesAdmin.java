import javax.swing.*;
import java.awt.*;


public class PaginaPrincipalSessoesAdmin {
    private JPanel mainPanel;
    private JButton adicionarButton;
    private JButton editarButton;
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel salasLabel = new JLabel("Sessões");
    private final AppWindow app;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private String nomeSessao;
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------


    //construtor
    public PaginaPrincipalSessoesAdmin(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {
        // pagina principal
        mainPanel.setLayout(new net.miginfocom.swing.MigLayout("nogrid, insets 0"));
        mainPanel.setBackground(corFundo);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));


        // seta andar para atras
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));

        // --------------------- ADMIN LABEL -----------------------
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminLabel.setForeground(corFundoLabel);
        adminLabel.setBackground(corFundo);
        adminLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        adminLabel.setOpaque(true);
        // --------------------- ADMIN LABEL -----------------------

        // --------------------- SALAS LABEL -----------------------
        salasLabel.setHorizontalAlignment(SwingConstants.CENTER);
        salasLabel.setForeground(corFundoLabel);
        salasLabel.setBackground(corFundo);
        salasLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        salasLabel.setOpaque(true);
        // --------------------- SALAS LABEL -----------------------


        //----------------- BOTAO ADICIONAR -------------
        adicionarButton = new RoundedButton("Criar", 20);
        adicionarButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        adicionarButton.setBackground(corFundoComponentes);
        adicionarButton.setForeground(corFonte); // texto
        //----------------- BOTAO ADICIONAR -------------


        //----------------- BOTAO EDITAR -------------
        editarButton = new RoundedButton("Editar", 20);
        editarButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        editarButton.setBackground(corFundoComponentes);
        editarButton.setForeground(corFonte); // texto
        //----------------- BOTAO EDITAR -------------


        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 500, y 40");
        mainPanel.add(salasLabel, "x 500, y 180");
        mainPanel.add(adicionarButton, "x 250, y 380, w 800, h 100");
        mainPanel.add(editarButton, "x 250, y 550, w 800, h 100");



        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                app.mostrarAdmin();
            }
        });

        // Redirecionar para AdicionarFilme
        adicionarButton.addActionListener(e -> app.mostrarAdicionarSessao());

        // Redirecionar para EditarFilme
        editarButton.addActionListener(e -> app.mostrarEditarSessao(nomeSessao));

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
