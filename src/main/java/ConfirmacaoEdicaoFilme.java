import javax.swing.*;
import java.awt.*;

public class ConfirmacaoEdicaoFilme {
    private JPanel mainPanel;
    private JLabel logoLabel;
    private JLabel voltaLabel;
    private JLabel adminLabel;
    private JLabel filmeEditarLabel;
    private JLabel filmeLabel;
    private JLabel alteradoLabel;
    private JLabel vistoConfirmacaoLabel;
    private final AppWindow app;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFontePreto = Color.decode("#000000");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    // construtor
    public ConfirmacaoEdicaoFilme(AppWindow app) {
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

        // --------------------- FILME ADICIONAR LABEL -----------------------
        filmeEditarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        filmeEditarLabel.setForeground(corFundoLabel);
        filmeEditarLabel.setBackground(corFundo);
        filmeEditarLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        filmeEditarLabel.setOpaque(true);
        // --------------------- FILME ADICIONAR LABEL -----------------------


        // --------------------- FILME ADICIONAR LABEL -----------------------
        filmeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        filmeLabel.setForeground(corFontePreto);
        filmeLabel.setBackground(corFundo);
        filmeLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        filmeLabel.setOpaque(true);
        // --------------------- FILME ADICIONAR LABEL -----------------------

        // --------------------- FILME ADICIONAR LABEL -----------------------
        alteradoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        alteradoLabel.setForeground(corFontePreto);
        alteradoLabel.setBackground(corFundo);
        alteradoLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        alteradoLabel.setOpaque(true);
        // --------------------- FILME ADICIONAR LABEL -----------------------

        // Logo
        ImageIcon vistoConfirmacaoIcon = new ImageIcon(getClass().getResource("/imagens/vistoConfirmacao.png"));
        Image vistoConfirmacaoImg = vistoConfirmacaoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        vistoConfirmacaoLabel.setIcon(new ImageIcon(vistoConfirmacaoImg));



        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(filmeEditarLabel, "x 450, y 110");
        mainPanel.add(filmeLabel, "x 420, y 320");
        mainPanel.add(alteradoLabel, "x 360, y 460");
        mainPanel.add(vistoConfirmacaoLabel, "x 800, y 300");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                app.mostrarPaginaPrincipalFilmesAdmin();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
