import javax.swing.*;
import java.awt.*;

public class PaginaPrincipalProdutosBarAdmin {
    private JPanel mainPanel;
    private final AppWindow app;

    private JLabel logoLabel;
    private JLabel voltaLabel;
    private JLabel titleLabel;
    private JLabel produtosBar;
    private JButton adicionarProduto;
    private JButton editarProduto;

    //construtor
    public PaginaPrincipalProdutosBarAdmin(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    private void configurarComponentes(){
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

        //--------------------- PRODUTOS BAR LABEL -----------------------
        produtosBar.setHorizontalAlignment(SwingConstants.CENTER);
        produtosBar.setForeground(corFundoLabel);
        produtosBar.setBackground(corFundo);
        produtosBar.setFont(new Font("Georgia", Font.PLAIN, 100));
        produtosBar.setOpaque(true);

        //--------------------- ADICIONAR PRODUTO BUTTON -----------------------
        adicionarProduto = new RoundedButton("Adicionar", 20);
        adicionarProduto.setFont(new Font("Georgia", Font.PLAIN, 35));
        adicionarProduto.setBackground(corFundoComponentes);
        adicionarProduto.setForeground(corFonte); // texto

        //--------------------- EDITAR PRODUTO BUTTON -----------------------
        editarProduto = new RoundedButton("Editar", 20);
        editarProduto.setFont(new Font("Georgia", Font.PLAIN, 35));
        editarProduto.setBackground(corFundoComponentes);
        editarProduto.setForeground(corFonte); // texto

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(titleLabel, "x 500, y 40");
        mainPanel.add(produtosBar, "x 400, y 200");
        mainPanel.add(adicionarProduto, "x 250, y 380, w 800, h 100");
        mainPanel.add(editarProduto, "x 250, y 550, w 800, h 100");

        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                app.mostrarAdmin();
            }
        });

        adicionarProduto.addActionListener(e -> app.mostrarPaginaAdicionarProduto());
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
