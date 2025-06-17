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

public class EditarSessaoAdmin {
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel sessoesLabel = new JLabel("Sessões - Editar");
    private JComboBox comboBoxEstado;
    private JTextField precoCompraField;
    private JLabel erroPrecoCompraLabel = new JLabel();
    private final String placeholder = "Preço do bilhete(€)";
    private JButton editarButton;

    private BaseDados bd = BaseDados.getInstance();

    private final AppWindow app;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private final Color corFontePreto = Color.decode("#000000");
    private final Color corFundoSubMenu = Color.decode("#FBDC95");
    private final Color corBotaoSetaComboBox = Color.decode("#F2AF14");
    private final Color corHoverComboBox = Color.decode("#FCD373");

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    //construtor
    public EditarSessaoAdmin(AppWindow app, Sessao sessaoAEditar) {
        this.app = app;
        configurarComponentes(sessaoAEditar);
    }

    private void configurarComponentes(Sessao sessaoAEditar) {

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

        // -------------------- ComboBox Estado --------------------------
        String[] opcoesEstado = {"Ativo", "Inativo"};
        comboBoxEstado = new RoundedComboBox<>(opcoesEstado, 20);

        // Não selecionar nenhum item no início → mostra placeholder
        comboBoxEstado.setSelectedItem(null);

        comboBoxEstado.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBox) {
                    @Override
                    public void show() {
                        // Tira a borda preta
                        setBorder(BorderFactory.createEmptyBorder());
                        setOpaque(false);
                        super.show();
                    }

                    @Override
                    public void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(corFundoSubMenu);
                        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                        g2.dispose();
                    }
                };

                popup.setBorder(BorderFactory.createEmptyBorder());
                popup.setOpaque(false);

                return popup;
            }

            @Override
            protected JButton createArrowButton() {
                return new JButton(new AdicionarFilme.ArrowIcon(comboBox)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        // Custom renderer com placeholder
        comboBoxEstado.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Estado");
                    label.setForeground(corFonte);
                    comboBoxEstado.setFont(new Font("Georgia", Font.PLAIN, 25));
                } else {
                    label.setForeground(corFontePreto);
                }

                if (index == -1) label.setBackground(corFundoComponentes);
                else if (isSelected) label.setBackground(corHoverComboBox);
                else label.setBackground(corFundoSubMenu);

                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                return label;
            }
        });

        comboBoxEstado.setFont(new Font("Georgia", Font.PLAIN, 25));
        comboBoxEstado.setForeground(corFontePreto);
        comboBoxEstado.setBackground(corFundoComponentes);


        comboBoxEstado.setEditable(false);
        // -------------------- ComboBox Estado --------------------------

        // --------------------- PRECO BILHETE ----------------
        precoCompraField = new RoundedTextField(1,20);
        if (sessaoAEditar.getPrecoBilhete() > 0) {
            String precoFormatado = String.format("%.2f €", sessaoAEditar.getPrecoBilhete()).replace(".", ",");
            precoCompraField.setText(precoFormatado);
            precoCompraField.setForeground(corFontePreto);
        } else {
            precoCompraField.setText(placeholder);
            precoCompraField.setForeground(corFonte);
        }

        precoCompraField.setHorizontalAlignment(SwingConstants.CENTER);
        precoCompraField.setBackground(corFundoComponentes);
        precoCompraField.setFont(new Font("Georgia", Font.PLAIN, 35));

        erroPrecoCompraLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        erroPrecoCompraLabel.setText("");
        erroPrecoCompraLabel.setForeground(Color.RED);
        erroPrecoCompraLabel.setHorizontalAlignment(SwingConstants.CENTER);
        erroPrecoCompraLabel.setVisible(false);

        precoCompraField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (precoCompraField.getText().equals(placeholder)) {
                    precoCompraField.setText("");
                    precoCompraField.setForeground(corFontePreto);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String textoBruto = precoCompraField.getText().trim();
                String textoLimpo = textoBruto.replace("€", "").replace(",", ".").replaceAll("[^\\d.-]", "");

                if (textoLimpo.isEmpty() || textoLimpo.equals("-") || textoLimpo.equals(".")) {
                    erroPrecoCompraLabel.setText("Insira um valor válido em euros.");
                    erroPrecoCompraLabel.setVisible(true);
                    precoCompraField.setText(placeholder);
                    precoCompraField.setForeground(corFonte);
                    return;
                }

                try {
                    double valor = Double.parseDouble(textoLimpo);
                    if (valor <= 0) {
                        erroPrecoCompraLabel.setText("Insira um valor superior a 0 em euros.");
                        erroPrecoCompraLabel.setVisible(true);
                        precoCompraField.setText(placeholder);
                        precoCompraField.setForeground(corFonte);
                    } else {
                        String valorFormatado = String.format("%.2f €", valor).replace(".", ",");
                        precoCompraField.setText(valorFormatado);
                        precoCompraField.setForeground(corFontePreto);
                        erroPrecoCompraLabel.setVisible(false);
                    }
                } catch (NumberFormatException ex) {
                    erroPrecoCompraLabel.setText("Insira um valor válido em euros.");
                    erroPrecoCompraLabel.setVisible(true);
                    precoCompraField.setText(placeholder);
                    precoCompraField.setForeground(corFonte);
                }
            }
        });
        // --------------------- PRECO BILHETE ----------------


        //----------------- BOTAO EDITAR -------------
        editarButton = new RoundedButton("Editar", 20);
        editarButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        editarButton.setBackground(corFundoLabel);
        editarButton.setForeground(corFontePreto); // texto
        editarButton.addActionListener(e -> {

            String precoTexto = precoCompraField.getText().trim();

            // Validações iniciais
            if (comboBoxEstado.getSelectedItem() == null || comboBoxEstado.getSelectedItem().toString().equals("Estado") ||
                    precoCompraField.getText().equals("Preço do bilhete(€)") || precoTexto.isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }



            String estadoTexto = (String) comboBoxEstado.getSelectedItem();
            Estado estadoSelecionado;

            if (estadoTexto.equalsIgnoreCase("Ativo")) {
                estadoSelecionado = Estado.ATIVO;
            } else if (estadoTexto.equalsIgnoreCase("Inativo")) {
                estadoSelecionado = Estado.INATIVO;
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um estado válido", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }


            // Converter precoTexto para float com tratamento de possíveis vírgulas e espaços
            float preco;
            try {
                precoTexto = precoTexto.replace("€", "").replace(",", ".").replaceAll("\\s+", "");
                preco = Float.parseFloat(precoTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Preço inválido", "Erro", JOptionPane.ERROR_MESSAGE);

                return;
            }


            //atualiza os dados
            bd.atualizarSessao(sessaoAEditar, preco, estadoSelecionado);

            bd.gravarDados();

            app.mostrarConfirmacaoEdicaoSessaoAdmin();
        });

        //----------------- BOTAO CRIAR -------------
        //----------------- BOTAO EDITAR -------------

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(sessoesLabel, "x 390, y 160");
        mainPanel.add(comboBoxEstado, "x 290, y 360, w 700, h 50");
        mainPanel.add(precoCompraField, "x 290, y 470, w 700, h 50");
        mainPanel.add(erroPrecoCompraLabel, "x 295, y 530, w 700, h 50");
        mainPanel.add(editarButton, "x 290, y 600, w 710, h 50");

        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarEscolherEditarSessaoAdmin();
            }
        });


    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
