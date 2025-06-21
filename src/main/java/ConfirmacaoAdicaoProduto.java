import javax.swing.*;
import java.awt.*;

public class ConfirmacaoAdicaoProduto {
    private final AppWindow app;

    private JLabel logoLabel;
    private JLabel voltaLabel;
    private JLabel adminLabel;
    private JLabel produtoAdicionarLabel;
    private JLabel produtoLabel;
    private JLabel adicionadoLabel;
    private JLabel vistoConfirmacaoLabel;
    private JPanel mainPanel;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFontePreto = Color.decode("#000000");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------


    public ConfirmacaoAdicaoProduto(AppWindow app) {
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
        adminLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        adminLabel.setOpaque(true);
        // --------------------- ADMIN LABEL -----------------------

        // --------------------- PRODUTO ADICIONAR LABEL -----------------------
        produtoAdicionarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        produtoAdicionarLabel.setForeground(corFundoLabel);
        produtoAdicionarLabel.setBackground(corFundo);
        produtoAdicionarLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        produtoAdicionarLabel.setOpaque(true);
        // --------------------- PRODUTO ADICIONAR LABEL -----------------------


        // --------------------- PRODUTO ADICIONAR LABEL -----------------------
        produtoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        produtoLabel.setForeground(corFontePreto);
        produtoLabel.setBackground(corFundo);
        produtoLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        produtoLabel.setOpaque(true);
        // --------------------- PRODUTO ADICIONAR LABEL -----------------------

        // --------------------- PRODUTO ADICIONAR LABEL -----------------------
        adicionadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adicionadoLabel.setForeground(corFontePreto);
        adicionadoLabel.setBackground(corFundo);
        adicionadoLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        adicionadoLabel.setOpaque(true);
        // --------------------- PRODUTO ADICIONAR LABEL -----------------------

        // Logo
        ImageIcon vistoConfirmacaoIcon = new ImageIcon(getClass().getResource("/imagens/vistoConfirmacao.png"));
        Image vistoConfirmacaoImg = vistoConfirmacaoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        vistoConfirmacaoLabel.setIcon(new ImageIcon(vistoConfirmacaoImg));



        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(produtoAdicionarLabel, "x 350, y 100");
        mainPanel.add(produtoLabel, "x 420, y 320");
        mainPanel.add(adicionadoLabel, "x 370, y 460");
        mainPanel.add(vistoConfirmacaoLabel, "x 830, y 300");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                app.mostrarPaginaPrincipalProdutosBarAdmin();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
