import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import net.miginfocom.swing.MigLayout;

public class AdminPaginaPrincipal {
    private JPanel mainPanel;
    private JButton salasButton;
    private JButton sessoesButton;
    private JButton filmesButton;
    private JButton produtosBarButton;
    private JLabel logoLabel;
    private JLabel voltaLabel;
    private JLabel titleLabel;
    private final AppWindow app;


    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------


    //construtor
    public AdminPaginaPrincipal(AppWindow app) {
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
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(corFundoLabel);
        titleLabel.setBackground(corFundo);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        titleLabel.setOpaque(true);
        // --------------------- ADMIN LABEL -----------------------


        //----------------- BOTAO SALAS -------------
        salasButton = new RoundedButton("Salas", 20);
        salasButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        salasButton.setBackground(corFundoComponentes);
        salasButton.setForeground(corFonte); // texto

        //----------------- BOTAO SALAS -------------

        //----------------- BOTAO SESSOES -------------
        sessoesButton = new RoundedButton("Sessões", 20);
        sessoesButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        sessoesButton.setBackground(corFundoComponentes);
        sessoesButton.setForeground(corFonte); // texto
        //----------------- BOTAO SESSOES -------------

        //----------------- BOTAO FILMES -------------
        filmesButton = new RoundedButton("Filmes", 20);
        filmesButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        filmesButton.setBackground(corFundoComponentes);
        filmesButton.setForeground(corFonte); // texto
        //----------------- BOTAO FILMES -------------

        //----------------- BOTAO PRODUTOS BAR -------------
        produtosBarButton = new RoundedButton("Produtos Bar", 20);
        produtosBarButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        produtosBarButton.setBackground(corFundoComponentes);
        produtosBarButton.setForeground(corFonte); // texto
        //----------------- BOTAO PRODUTOS BAR -------------

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(titleLabel, "x 500, y 40");
        mainPanel.add(salasButton, "x 150, y 310, w 450, h 130");
        mainPanel.add(sessoesButton, "x 700, y 310, w 450, h 130");
        mainPanel.add(filmesButton, "x 150, y 530, w 450, h 130");
        mainPanel.add(produtosBarButton, "x 700, y 530, w 450, h 130");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                app.mostrarPaginaInicial();
            }
        });

        // Redirecionar para PaginaPrincipalSalasAdmin
        salasButton.addActionListener(e -> app.mostrarPaginaPrincipalSalasAdmin());

        // Redirecionar para PaginaPrincipalFilmesAdmin
        filmesButton.addActionListener(e -> app.mostrarPaginaPrincipalFilmesAdmin());

        // Redirecionar para PaginaPrincipalSessoesAdmin
        sessoesButton.addActionListener(e -> app.mostrarPaginaPrincipalSessoesAdmin());

        // Redirecionar para PaginaPrincipalProdutosBarAdmin
        produtosBarButton.addActionListener(e -> app.mostrarPaginaPrincipalProdutosBarAdmin());

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
