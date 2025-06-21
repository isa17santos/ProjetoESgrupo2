import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class ConfirmacaoCriacaoSessao {
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel sessoesLabel = new JLabel("Sessões - Criação");
    private JLabel adicionadoLabel = new JLabel("Sessão criada");
    private JLabel sucessoLabel = new JLabel("com sucesso");
    private JLabel vistoConfirmacaoLabel = new JLabel();


    private final AppWindow app;


    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFontePreto = Color.decode("#000000");

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    //construtor
    public ConfirmacaoCriacaoSessao(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }


    private void configurarComponentes() {

        // pagina principal
        mainPanel.setLayout(new MigLayout("nogrid, insets 0"));
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

        // --------------------- SALAS LABEL -----------------------
        sessoesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sessoesLabel.setForeground(corFundoLabel);
        sessoesLabel.setBackground(corFundo);
        sessoesLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        sessoesLabel.setOpaque(true);
        // --------------------- SALAS LABEL -----------------------

        // --------------------- ADICIONAR LABEL -----------------------
        adicionadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adicionadoLabel.setForeground(corFontePreto);
        adicionadoLabel.setBackground(corFundo);
        adicionadoLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        adicionadoLabel.setOpaque(true);
        // --------------------- ADICIONAR LABEL -----------------------

        // ---------------------  ADICIONAR LABEL -----------------------
        sucessoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sucessoLabel.setForeground(corFontePreto);
        sucessoLabel.setBackground(corFundo);
        sucessoLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        sucessoLabel.setOpaque(true);
        // ---------------------  ADICIONAR LABEL -----------------------

        // Logo
        ImageIcon vistoConfirmacaoIcon = new ImageIcon(getClass().getResource("/imagens/vistoConfirmacao.png"));
        Image vistoConfirmacaoImg = vistoConfirmacaoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        vistoConfirmacaoLabel.setIcon(new ImageIcon(vistoConfirmacaoImg));




        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(sessoesLabel, "x 450, y 110");
        mainPanel.add(adicionadoLabel, "x 310, y 320");
        mainPanel.add(sucessoLabel, "x 330, y 450");
        mainPanel.add(vistoConfirmacaoLabel, "x 800, y 300");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaPrincipalSessaoAdmin();
            }
        });


    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}


