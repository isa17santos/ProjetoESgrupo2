import javax.swing.*;
import java.awt.*;

public class ConfirmacaoCarrinhoPagamento {
    private final AppWindow app;

    private JLabel logoLabel;
    private JLabel voltaLabel;
    private JLabel vistoConfirmacaoLabel;
    private JPanel mainPanel;
    private JLabel pagamentoLabel;
    private JLabel carrinhoLabel;
    private JLabel pagoLabel;
    private JLabel sucessoLabel;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFontePreto = Color.decode("#000000");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------


    public ConfirmacaoCarrinhoPagamento(AppWindow app) {
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
        carrinhoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        carrinhoLabel.setForeground(corFundoLabel);
        carrinhoLabel.setBackground(corFundo);
        carrinhoLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        carrinhoLabel.setOpaque(true);
        // --------------------- ADMIN LABEL -----------------------

        // --------------------- carrinho LABEL -----------------------
        carrinhoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        carrinhoLabel.setForeground(corFundoLabel);
        carrinhoLabel.setBackground(corFundo);
        carrinhoLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        carrinhoLabel.setOpaque(true);
        // --------------------- carrinho LABEL -----------------------


        // --------------------- pagamento  LABEL -----------------------
        pagamentoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pagamentoLabel.setForeground(corFundoLabel);
        pagamentoLabel.setBackground(corFundo);
        pagamentoLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        pagamentoLabel.setOpaque(true);
        // --------------------- pagamento LABEL -----------------------

        // ---------------------  LABEL -----------------------
        pagoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pagoLabel.setForeground(corFontePreto);
        pagoLabel.setBackground(corFundo);
        pagoLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        pagoLabel.setOpaque(true);

        sucessoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sucessoLabel.setForeground(corFontePreto);
        sucessoLabel.setBackground(corFundo);
        sucessoLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        sucessoLabel.setOpaque(true);
        // ---------------------  LABEL -----------------------

        // Logo
        ImageIcon vistoConfirmacaoIcon = new ImageIcon(getClass().getResource("/imagens/vistoConfirmacao.png"));
        Image vistoConfirmacaoImg = vistoConfirmacaoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        vistoConfirmacaoLabel.setIcon(new ImageIcon(vistoConfirmacaoImg));



        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(carrinhoLabel, "x 500, y 20");
        mainPanel.add(pagamentoLabel, "x 450, y 120");
        mainPanel.add(pagoLabel, "x 150, y 360");
        mainPanel.add(sucessoLabel, "x 300, y 460");
        mainPanel.add(vistoConfirmacaoLabel, "x 930, y 300");


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
