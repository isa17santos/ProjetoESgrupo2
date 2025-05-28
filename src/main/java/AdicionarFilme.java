import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.image.BufferedImage;

public class AdicionarFilme {
    private JPanel mainPanel;
    private JLabel logoLabel;
    private JLabel voltaLabel;
    private JLabel adminLabel;
    private JLabel filmesLabel;
    private JButton adicionarButton;
    private JButton uploadButton;
    private JTextField nomeFilme;
    private JTextField duracaoFilme;
    private JLabel erroLabel;
    private JButton comboButtonIdioma;
    private JComboBox comboBoxIdade;

    private final AppWindow app;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private final Color corFontePreto = Color.decode("#000000");
    private final Color corFundoUploadButton = Color.decode("#E3E3E3");
    private final Color corFundoSubMenu = Color.decode("#FBDC95");
    private final Color corBotaoSetaComboBox = Color.decode("#F2AF14");
    private final Color corHoverComboBox = Color.decode("#FCD373");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------



    //construtor
    public AdicionarFilme(AppWindow app) {
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

        // --------------------- FILMES LABEL -----------------------
        filmesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        filmesLabel.setForeground(corFundoLabel);
        filmesLabel.setBackground(corFundo);
        filmesLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        filmesLabel.setOpaque(true);
        // --------------------- FILMES LABEL -----------------------


        //----------------- BOTAO ADICIONAR -------------
        adicionarButton = new RoundedButton("Adicionar", 20);
        adicionarButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        adicionarButton.setBackground(corFundoLabel);
        adicionarButton.setForeground(corFontePreto); // texto
        //----------------- BOTAO ADICIONAR -------------


        //---------------------------- BOTAO UPLOAD ------------------------------
        uploadButton = new JButton("Carregar Cartaz");
        uploadButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        uploadButton.setBackground(corFundoUploadButton);
        uploadButton.setForeground(corFontePreto);
        uploadButton.setFocusPainted(false);
        uploadButton.setContentAreaFilled(true);
        uploadButton.setBorderPainted(false);

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Selecione uma imagem");
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png");
                fileChooser.addChoosableFileFilter(filter);

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage img = ImageIO.read(selectedFile);
                        if (img != null) {
                            uploadButton.setOpaque(false);
                            Image scaledImg = img.getScaledInstance(290, 410, Image.SCALE_SMOOTH);
                            ImageIcon icon = new ImageIcon(scaledImg);
                            uploadButton.setIcon(icon);
                            uploadButton.setText(""); // remove texto
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao carregar imagem.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao carregar imagem: " + ex.getMessage());
                    }
                }
            }
        });
        //---------------------------- BOTAO UPLOAD ------------------------------

        // --------------- CAIXA DE TEXTO NOME FILME --------------
        nomeFilme = new RoundedTextField(1,20);
        nomeFilme.setHorizontalAlignment(SwingConstants.CENTER);
        nomeFilme.setBackground(corFundoComponentes);
        nomeFilme.setFont(new Font("Georgia", Font.PLAIN, 35));
        nomeFilme.setText("Nome filme");
        nomeFilme.setForeground(corFonte); // texto
        nomeFilme.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nomeFilme.getText().equals("Nome filme")) {
                    nomeFilme.setText("");
                    nomeFilme.setForeground(corFontePreto); // Cor real do texto do utilizador
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nomeFilme.getText().isEmpty()) {
                    nomeFilme.setText("Nome filme");
                    nomeFilme.setForeground(corFonte); // Placeholder de novo
                }
            }
        });
        // --------------- CAIXA DE TEXTO NOME FILME --------------

        // --------------- CAIXA DE TEXTO DURACAO --------------
        duracaoFilme = new RoundedTextField(1,20);
        duracaoFilme.setHorizontalAlignment(SwingConstants.CENTER);
        duracaoFilme.setBackground(corFundoComponentes);
        duracaoFilme.setFont(new Font("Georgia", Font.PLAIN, 25));
        duracaoFilme.setText("Duração (minutos)");
        duracaoFilme.setForeground(corFonte); // texto

        erroLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        erroLabel.setText("");
        erroLabel.setBackground(corFundo);
        erroLabel.setForeground(Color.RED);
        erroLabel.setHorizontalAlignment(SwingConstants.CENTER);
        erroLabel.setVisible(false);

        duracaoFilme.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (duracaoFilme.getText().equals("Duração (minutos)")) {
                    duracaoFilme.setText("");
                    duracaoFilme.setForeground(corFontePreto); // Cor real do texto do utilizador
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String input = duracaoFilme.getText();

                if (input.isEmpty()) {
                    duracaoFilme.setText("Duração (minutos)");
                    duracaoFilme.setForeground(corFonte);
                    erroLabel.setVisible(false);
                    return;
                }

                try {
                    int duracao = Integer.parseInt(input);
                    if (duracao <= 0) {
                        erroLabel.setText("Insira um valor superior a 0.");
                        erroLabel.setVisible(true);
                        duracaoFilme.setText("Duração (minutos)");
                        duracaoFilme.setForeground(corFonte);
                    } else {
                        erroLabel.setVisible(false); // valor válido
                    }
                } catch (NumberFormatException ex) {
                    erroLabel.setText("Insira um valor numérico válido.");
                    erroLabel.setVisible(true);
                    duracaoFilme.setText("Duração (minutos)");
                    duracaoFilme.setForeground(corFonte);
                }
            }
        });
        // --------------- CAIXA DE TEXTO DURACAO --------------


        // ---------------------------- JCheckBox Idioma -------------------------------
        JCheckBox[] checkBoxes = {
                new JCheckBox("Português (VP)"),
                new JCheckBox("Inglês (VO)")
        };

        RoundedPanel checkBoxIdioma = new RoundedPanel(20);
        checkBoxIdioma.setLayout(new BoxLayout(checkBoxIdioma, BoxLayout.Y_AXIS));
        checkBoxIdioma.setBackground(corFundoSubMenu);
        checkBoxIdioma.setBorder(BorderFactory.createEmptyBorder(5, 30, 0, 0));
        for (JCheckBox cb : checkBoxes) {
            cb.setOpaque(false);
            cb.setBackground(corFundoSubMenu);
            cb.setHorizontalAlignment(SwingConstants.CENTER);
            cb.setFont(new Font("Georgia", Font.PLAIN, 25));
            cb.setIconTextGap(30); // aumenta espaço entre quadrado e texto
            cb.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 0)); // top, left, bottom, right
            checkBoxIdioma.add(cb);
        }

        JPopupMenu popupMenu = new JPopupMenu() {
            {
                setOpaque(false); // evita fundo quadrado
                setBorder(BorderFactory.createEmptyBorder()); // sem bordas duras
            }

            @Override
            public void paintComponent(Graphics g) {
                // pinta fundo arredondado
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(corFundoSubMenu); // a tua cor definida
                g2.fill(shape);
                g2.dispose();
            }
        };

        popupMenu.add(checkBoxIdioma);
        popupMenu.add(checkBoxIdioma);

        // Recalcular altura com base nos componentes adicionados
        checkBoxIdioma.revalidate();
        checkBoxIdioma.repaint();

        // Define largura e altura com base no conteúdo
        int popupWidth = 340;
        int popupHeight = checkBoxIdioma.getPreferredSize().height + 20; // margem extra opcional

        popupMenu.setPreferredSize(new Dimension(popupWidth, popupHeight));

        popupMenu.setBackground(corFundoSubMenu);

        comboButtonIdioma = new RoundedButton("Idioma", 20);
        comboButtonIdioma.setFont(new Font("Georgia", Font.PLAIN, 35));
        comboButtonIdioma.setBackground(corFundoComponentes);
        comboButtonIdioma.setForeground(Color.BLACK);
        comboButtonIdioma.setHorizontalAlignment(SwingConstants.CENTER);

        boolean[] isMenuVisible = {false};

        comboButtonIdioma.addActionListener(e -> {
            if (isMenuVisible[0]) {
                popupMenu.setVisible(false);
                isMenuVisible[0] = false;
            } else {
                popupMenu.setPreferredSize(new Dimension(comboButtonIdioma.getWidth(), popupMenu.getPreferredSize().height));
                popupMenu.show(comboButtonIdioma, 0, comboButtonIdioma.getHeight());
                isMenuVisible[0] = true;
            }
        });

        // Atualizado: só mostra "VP" ou "VO" no botão
        for (JCheckBox cb : checkBoxes) {
            cb.addItemListener(e -> {
                StringBuilder sb = new StringBuilder();
                for (JCheckBox c : checkBoxes) {
                    if (c.isSelected()) {
                        // Extrai o conteúdo entre parênteses, ex: "VP", "VO"
                        String text = c.getText();
                        int start = text.indexOf('(');
                        int end = text.indexOf(')');
                        if (start != -1 && end != -1 && start < end) {
                            sb.append(text, start + 1, end).append(", ");
                        }
                    }
                }
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 2);
                    comboButtonIdioma.setText(sb.toString());
                } else {
                    comboButtonIdioma.setText("Idioma");
                }
            });
        }

        comboButtonIdioma.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                popupMenu.setPreferredSize(new Dimension(comboButtonIdioma.getWidth(), popupMenu.getPreferredSize().height));
            }
        });
        // ---------------------------- JCheckBox Idioma -------------------------------


        // -------------------- ComboBox idade --------------------------
        String[] opcoes = {"6+", "12+", "16+", "18+"};
        comboBoxIdade = new RoundedComboBox<>(opcoes, 20);

        // Não selecionar nenhum item no início → mostra placeholder
        comboBoxIdade.setSelectedItem(null);

        comboBoxIdade.setUI(new BasicComboBoxUI() {
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
                return new JButton(new ArrowIcon(comboBox)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        // Custom renderer com placeholder
        comboBoxIdade.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Idade");
                    label.setForeground(corFonte);
                    comboBoxIdade.setFont(new Font("Georgia", Font.PLAIN, 35));
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

        comboBoxIdade.setFont(new Font("Georgia", Font.PLAIN, 25));
        comboBoxIdade.setBackground(corFundoComponentes);

        // Se quiseres o campo editável (não necessário para placeholder funcionar):
        comboBoxIdade.setEditable(false); // ou true se necessário
        // -------------------- ComboBox idade --------------------------




        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(filmesLabel, "x 450, y 110");
        mainPanel.add(adicionarButton, "x 1100, y 210, w 70, h 30");
        mainPanel.add(uploadButton, "x 100, y 290, w 110, h 400");
        mainPanel.add(nomeFilme, "x 450, y 290, w 700, h 50");
        mainPanel.add(duracaoFilme, "x 450, y 360, w 340, h 50");
        mainPanel.add(erroLabel, "x 490, y 405, w 100, h 40");
        mainPanel.add(comboButtonIdioma, "x 810, y 360, w 340, h 50");
        mainPanel.add(comboBoxIdade, "x 450, y 450, w 340, h 50");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        //voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            //@Override
            //public void mouseClicked(java.awt.event.MouseEvent e) {
                //app.mostrarAdmin();
            //}
        //});

        // Redirecionar para AdicionarFilme
        //adicionarButton.addActionListener(e -> app.mostrarAdicionarFilmes());

    }

    // FUNCAO QUE CRIA E MANIPULA DAS SETAS DOS JComboBoxes
    static class ArrowIcon implements Icon {
        private final int size = 10;
        private final JComboBox<?> comboBox;

        public ArrowIcon(JComboBox<?> comboBox) {
            this.comboBox = comboBox;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.BLACK);
            boolean isPopupVisible = comboBox.isPopupVisible();
            g2.translate(x + size / 2, y + size / 2);
            if (isPopupVisible) g2.rotate(Math.PI);
            g2.translate(-(x + size / 2), -(y + size / 2));
            int[] xPoints = {x, x + size, x + size / 2};
            int[] yPoints = {y, y, y + size};
            g2.fillPolygon(xPoints, yPoints, 3);
            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

