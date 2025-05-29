import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AdicionarSala {
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel salasLabel = new JLabel("Salas - Criação");
    private JTextField nomeSala = new JTextField(20);
    private JTextField numeroFilas = new JTextField(20);
    private JTextField numeroLugaresFila = new JTextField(20);
    private JButton adicionarButton = new JButton("Adicionar");
    private JLabel erroLabel = new JLabel("Erro: Insira valores válidos.");

    private final AppWindow app;

    private String input = null;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private final Color corFontePreto = Color.decode("#000000");
    private final Color corFundoUploadButton = Color.decode("#E3E3E3");

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    //construtor
    public AdicionarSala(AppWindow app) {
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

        // --------------------- SALAS LABEL -----------------------
        salasLabel.setHorizontalAlignment(SwingConstants.CENTER);
        salasLabel.setForeground(corFundoLabel);
        salasLabel.setBackground(corFundo);
        salasLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        salasLabel.setOpaque(true);
        // --------------------- SALAS LABEL -----------------------

        // --------------- CAIXA DE TEXTO NOME SALA --------------
        nomeSala = new RoundedTextField(1,20);
        nomeSala.setHorizontalAlignment(SwingConstants.CENTER);
        nomeSala.setBackground(corFundoComponentes);
        nomeSala.setFont(new Font("Georgia", Font.PLAIN, 35));
        nomeSala.setText("Designação");
        nomeSala.setForeground(corFonte); // texto
        nomeSala.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nomeSala.getText().equals("Designação")) {
                    nomeSala.setText("");
                    nomeSala.setForeground(corFontePreto); // Cor real do texto do utilizador
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nomeSala.getText().isEmpty()) {
                    nomeSala.setText("Designação");
                    nomeSala.setForeground(corFonte); // Placeholder de novo
                }
            }
        });

        // --------------- CAIXA DE TEXTO NOME SALA --------------

        // --------------- CAIXA DE TEXTO NÚMERO DE FILAS --------------

        numeroFilas = new RoundedTextField(1,20);
        numeroFilas.setHorizontalAlignment(SwingConstants.CENTER);
        numeroFilas.setBackground(corFundoComponentes);
        numeroFilas.setFont(new Font("Georgia", Font.PLAIN, 35));
        numeroFilas.setText("Nº Filas");
        numeroFilas.setForeground(corFonte); // texto
        numeroFilas.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (numeroFilas.getText().equals("Nº Filas")) {
                    numeroFilas.setText("");
                    numeroFilas.setForeground(corFontePreto); // Cor real do texto do utilizador
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                input = numeroFilas.getText();
                if (input.isEmpty()) {
                    numeroFilas.setText("Nº Filas");
                    numeroFilas.setForeground(corFonte); // Placeholder de novo
                }

                try {
                    int duracao = Integer.parseInt(input);
                    if (duracao <= 0) {
                        erroLabel.setText("Insira um valor superior a 0.");
                        erroLabel.setVisible(true);
                        numeroFilas.setText("Nº Filas");
                        numeroFilas.setForeground(corFonte);
                    } else {
                        erroLabel.setVisible(false); // valor válido
                    }
                } catch (NumberFormatException ex) {
                    erroLabel.setText("Insira um valor numérico válido.");
                    erroLabel.setVisible(true);
                    numeroFilas.setText("Nº Filas");
                    numeroFilas.setForeground(corFonte);
                }
            }
        });
        // --------------- CAIXA DE TEXTO NÚMERO DE FILAS --------------

        // --------------- CAIXA DE TEXTO NÚMERO DE LUGARES POR FILA --------------

        numeroLugaresFila = new RoundedTextField(1,20);
        numeroLugaresFila.setHorizontalAlignment(SwingConstants.CENTER);
        numeroLugaresFila.setBackground(corFundoComponentes);
        numeroLugaresFila.setFont(new Font("Georgia", Font.PLAIN, 35));
        numeroLugaresFila.setText("Nº Lugares por Fila");
        numeroLugaresFila.setForeground(corFonte); // texto
        numeroLugaresFila.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (numeroLugaresFila.getText().equals("Nº Lugares por Fila")) {
                    numeroLugaresFila.setText("");
                    numeroLugaresFila.setForeground(corFontePreto); // Cor real do texto do utilizador
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                input = numeroLugaresFila.getText();
                if (input.isEmpty()) {
                    numeroLugaresFila.setText("Nº Lugares por Fila");
                    numeroLugaresFila.setForeground(corFonte); // Placeholder de novo
                }

                try {
                    int duracao = Integer.parseInt(input);
                    if (duracao <= 0) {
                        erroLabel.setText("Insira um valor superior a 0.");
                        erroLabel.setVisible(true);
                        numeroLugaresFila.setText("Nº Lugares por Fila");
                        numeroLugaresFila.setForeground(corFonte);
                    } else {
                        erroLabel.setVisible(false); // valor válido
                    }
                } catch (NumberFormatException ex) {
                    erroLabel.setText("Insira um valor numérico válido.");
                    erroLabel.setVisible(true);
                    numeroLugaresFila.setText("Nº Lugares por Fila");
                    numeroLugaresFila.setForeground(corFonte);
                }
            }
        });
        // --------------- CAIXA DE TEXTO NÚMERO DE LUGARES POR FILA --------------

        // ----------------- ERRO LABEL ----------

        erroLabel.setHorizontalAlignment(SwingConstants.CENTER);
        erroLabel.setForeground(Color.RED);
        erroLabel.setFont(new Font("Georgia", Font.PLAIN, 25));
        erroLabel.setVisible(false); // Inicialmente invisível
        erroLabel.setOpaque(true);
        erroLabel.setBackground(corFundo); // Fundo para destacar o erro

        // ----------------- ERRO LABEL ----------

        // ----------------- ECRÃ DROPDOWN ----------

        /* String[] opcoes = {"Opção 1", "Opção 2", "Opção 3"};
        comboBox.removeAllItems();
        for (String opcao : opcoes) {
            comboBox.addItem(opcao);
        }
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton(new ArrowIcon(comboBox)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setForeground(Color.BLACK);
                if (index == -1) c.setBackground(corFundoComponentes);
                else if (isSelected) c.setBackground(corHoverComboBox);
                else c.setBackground(corFundoSubMenu);
                if (c instanceof JComponent) ((JComponent) c).setOpaque(true);
                return c;
            }
        });

        comboBox.setEditable(true);
        comboBox.setBackground(corFundoComponentes);
        comboBox.setBorder(rounded);
        JTextField editor = (JTextField) comboBox.getEditor().getEditorComponent();
        editor.setForeground(Color.BLACK);
        editor.setBackground(corFundoComponentes);
        editor.setOpaque(true);
        editor.setBorder(null);
        editor.setEditable(false);
        editor.setHorizontalAlignment(SwingConstants.CENTER);

        // ----------------- ECRÃ DROPDOWN ---------- */


        //----------------- BOTAO ADICIONAR -------------
        adicionarButton = new RoundedButton("Adicionar", 20);
        adicionarButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        adicionarButton.setBackground(corFundoLabel);
        adicionarButton.setForeground(corFontePreto); // texto
        //----------------- BOTAO ADICIONAR -------------

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(salasLabel, "x 450, y 110");
        mainPanel.add(nomeSala, "x 250, y 250, w 800, h 50");
        mainPanel.add(numeroFilas, "x 250, y 350, w 350, h 50");
        mainPanel.add(numeroLugaresFila, "x 700, y 350, w 350, h 50");
        mainPanel.add(adicionarButton, "x 250, y 650, w 800, h 50");
        mainPanel.add(erroLabel, "x 250, y 750, w 800, h 50");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                app.mostrarPaginaPrincipalSalasAdmin();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
