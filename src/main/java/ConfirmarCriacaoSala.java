import javax.swing.*;
import java.awt.*;

public class ConfirmarCriacaoSala {

    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel salasLabel = new JLabel("Salas - Criação");
    private JLabel confirmacaoLabel = new JLabel();
    private JLabel imagemConfirmacaoLabel = new JLabel();
    private final AppWindow app;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFontePreto = Color.decode("#000000");

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    // Construtor
    public ConfirmarCriacaoSala(AppWindow app, String nomeSala) {
        this.app = app;
        confirmacaoLabel.setText("<html>" + nomeSala + " criada" + "<br>com sucesso!</html>");
        configurarComponentes();
    }

    private void configurarComponentes() {
        // Página principal
        mainPanel.setLayout(new net.miginfocom.swing.MigLayout("nogrid, insets 0"));
        mainPanel.setBackground(corFundo);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));

        // Seta andar para trás
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));

        // admin label
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminLabel.setForeground(corFundoLabel);
        adminLabel.setBackground(corFundo);
        adminLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        adminLabel.setOpaque(true);

        // salas label
        salasLabel.setHorizontalAlignment(SwingConstants.CENTER);
        salasLabel.setForeground(corFundoLabel);
        salasLabel.setBackground(corFundo);
        salasLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        salasLabel.setOpaque(true);

        // confirmação label
        confirmacaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmacaoLabel.setForeground(corFontePreto);
        confirmacaoLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        confirmacaoLabel.setBackground(corFundo);
        confirmacaoLabel.setBackground(corFundo);

        // imagem confirmação
        ImageIcon confirmacaoIcon = new ImageIcon(getClass().getResource("/imagens/vistoConfirmacao.png"));
        Image confirmacaoImg = confirmacaoIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imagemConfirmacaoLabel.setIcon(new ImageIcon(confirmacaoImg));

        // Reposicionamento dos componentes
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(salasLabel, "x 400, y 120");
        mainPanel.add(confirmacaoLabel, "x 100, y 300, w 800, h 400");
        mainPanel.add(imagemConfirmacaoLabel, "x 800, y 300, w 300, h 400");

        // Ação do botão de voltar
        voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                app.mostrarPaginaPrincipalSalasAdmin();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
