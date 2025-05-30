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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;

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
    private JLabel erroDuracaoLabel;
    private JButton comboButtonIdioma;
    private JComboBox comboBoxIdade;
    private JButton comboButtonGenero;
    private JButton comboButtonTipo;
    private JComboBox comboBoxEstado;
    private JTextField precoCompraField;
    private JLabel erroPrecoCompraLabel;

    private final String placeholder = "Preço da compra(€)";
    private BufferedImage imagemCarregada = null;
    private String nomeFicheiroImagem = null;
    private File ficheiroImagemOriginal = null;


    private BaseDados bd;

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
                            Image scaledImg = img.getScaledInstance(290, 420, Image.SCALE_SMOOTH);
                            ImageIcon icon = new ImageIcon(scaledImg);
                            uploadButton.setIcon(icon);
                            uploadButton.setText(""); // remove texto
                            imagemCarregada = img;
                            ficheiroImagemOriginal = selectedFile;  // ficheiro completo com caminho
                            nomeFicheiroImagem = selectedFile.getName(); // nome simples
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

        erroDuracaoLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        erroDuracaoLabel.setText("");
        erroDuracaoLabel.setBackground(corFundo);
        erroDuracaoLabel.setForeground(Color.RED);
        erroDuracaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        erroDuracaoLabel.setVisible(false);

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
                    erroDuracaoLabel.setVisible(false);
                    return;
                }

                try {
                    int duracao = Integer.parseInt(input);
                    if (duracao <= 0) {
                        erroDuracaoLabel.setText("Insira um valor superior a 0.");
                        erroDuracaoLabel.setVisible(true);
                        duracaoFilme.setText("Duração (minutos)");
                        duracaoFilme.setForeground(corFonte);
                    } else {
                        erroDuracaoLabel.setVisible(false); // valor válido
                    }
                } catch (NumberFormatException ex) {
                    erroDuracaoLabel.setText("Insira um valor numérico válido.");
                    erroDuracaoLabel.setVisible(true);
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

        comboBoxIdade.setEditable(false);
        // -------------------- ComboBox idade --------------------------


        // ---------------------------- JCheckBox Genero -------------------------------
        JCheckBox[] checkBoxesGenero = {
                new JCheckBox("Animação"),
                new JCheckBox("Ação"),
                new JCheckBox("Comédia"),
                new JCheckBox("Terror"),
                new JCheckBox("Romance"),
                new JCheckBox("Ficção Científica")
        };

        RoundedPanel checkBoxGenero = new RoundedPanel(20);
        checkBoxGenero.setLayout(new BoxLayout(checkBoxGenero, BoxLayout.Y_AXIS));
        checkBoxGenero.setBackground(corFundoSubMenu);
        checkBoxGenero.setBorder(BorderFactory.createEmptyBorder(5, 30, 0, 0));
        for (JCheckBox cb : checkBoxesGenero) {
            cb.setOpaque(false);
            cb.setBackground(corFundoSubMenu);
            cb.setHorizontalAlignment(SwingConstants.CENTER);
            cb.setFont(new Font("Georgia", Font.PLAIN, 25));
            cb.setIconTextGap(30); // aumenta espaço entre quadrado e texto
            cb.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); // top, left, bottom, right
            checkBoxGenero.add(cb);
        }

        JPopupMenu popupMenuGenero = new JPopupMenu() {
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

        popupMenuGenero.add(checkBoxGenero);
        popupMenuGenero.add(checkBoxGenero);

        // Recalcular altura com base nos componentes adicionados
        checkBoxGenero.revalidate();
        checkBoxGenero.repaint();

        // Define largura e altura com base no conteúdo
        int popupGeneroWidth = 340;
        int popupGeneroHeight = checkBoxGenero.getPreferredSize().height + 8; // margem extra opcional

        popupMenuGenero.setPreferredSize(new Dimension(popupGeneroWidth, popupGeneroHeight));

        popupMenuGenero.setBackground(corFundoSubMenu);

        comboButtonGenero = new RoundedButton("Género", 20);
        comboButtonGenero.setFont(new Font("Georgia", Font.PLAIN, 35));
        comboButtonGenero.setBackground(corFundoComponentes);
        comboButtonGenero.setForeground(Color.BLACK);
        comboButtonGenero.setHorizontalAlignment(SwingConstants.CENTER);

        boolean[] isMenuGeneroVisible = {false};

        comboButtonGenero.addActionListener(e -> {
            if (isMenuGeneroVisible[0]) {
                popupMenuGenero.setVisible(false);
                isMenuGeneroVisible[0] = false;
            } else {
                popupMenuGenero.setPreferredSize(new Dimension(comboButtonGenero.getWidth(), popupMenuGenero.getPreferredSize().height));
                popupMenuGenero.show(comboButtonGenero, 0, comboButtonGenero.getHeight());
                isMenuGeneroVisible[0] = true;
            }
        });

        // Função para ajustar texto ao espaço do botão
        Runnable updateGeneroButtonText = () -> {
            StringBuilder fullText = new StringBuilder();
            for (JCheckBox c : checkBoxesGenero) {
                if (c.isSelected()) {
                    fullText.append(c.getText()).append(", ");
                }
            }

            if (fullText.length() > 0) {
                fullText.setLength(fullText.length() - 2); // remove ", "
                String full = fullText.toString();

                // Medir texto e ajustar
                FontMetrics fm = comboButtonGenero.getFontMetrics(comboButtonGenero.getFont());
                int availableWidth = comboButtonGenero.getWidth() - 16; // margem de segurança
                String displayText = "";
                String[] parts = full.split(", ");
                StringBuilder current = new StringBuilder();
                for (int i = 0; i < parts.length; i++) {
                    String test = current + (current.length() > 0 ? ", " : "") + parts[i];
                    if (fm.stringWidth(test) > availableWidth) {
                        displayText = current + "...";
                        break;
                    }
                    current.append((current.length() > 0 ? ", " : "")).append(parts[i]);
                    displayText = current.toString();
                }

                comboButtonGenero.setText(displayText);
            } else {
                comboButtonGenero.setText("Género");
            }

            // Atualizar o tamanho da fonte conforme o texto
            if (comboButtonGenero.getText().equals("Género")) {
                comboButtonGenero.setFont(new Font("Georgia", Font.PLAIN, 35));
            } else {
                comboButtonGenero.setFont(new Font("Georgia", Font.PLAIN, 22));
            }
        };

        // Atualizar texto quando opções mudam
        for (JCheckBox cb : checkBoxesGenero) {
            cb.addItemListener(e -> updateGeneroButtonText.run());
        }

        // Ajustar texto ao redimensionar
        comboButtonGenero.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                popupMenuGenero.setPreferredSize(new Dimension(comboButtonGenero.getWidth(), popupMenuGenero.getPreferredSize().height));
                updateGeneroButtonText.run();
            }
        });
        // ---------------------------- JCheckBox Genero -------------------------------


        // ---------------------------- JCheckBox Tipo -------------------------------
        JCheckBox[] checkBoxesTipo = {
                new JCheckBox("2D"),
                new JCheckBox("3D"),
                new JCheckBox("5D")
        };

        RoundedPanel checkBoxTipo = new RoundedPanel(20);
        checkBoxTipo.setLayout(new BoxLayout(checkBoxTipo, BoxLayout.Y_AXIS));
        checkBoxTipo.setBackground(corFundoSubMenu);
        checkBoxTipo.setBorder(BorderFactory.createEmptyBorder(5, 30, 0, 0));
        for (JCheckBox cb : checkBoxesTipo) {
            cb.setOpaque(false);
            cb.setBackground(corFundoSubMenu);
            cb.setHorizontalAlignment(SwingConstants.CENTER);
            cb.setFont(new Font("Georgia", Font.PLAIN, 25));
            cb.setIconTextGap(30); // aumenta espaço entre quadrado e texto
            cb.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); // top, left, bottom, right
            checkBoxTipo.add(cb);
        }

        JPopupMenu popupMenuTipo = new JPopupMenu() {
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

        popupMenuTipo.add(checkBoxTipo);
        popupMenuTipo.add(checkBoxTipo);

        // Recalcular altura com base nos componentes adicionados
        checkBoxTipo.revalidate();
        checkBoxTipo.repaint();

        // Define largura e altura com base no conteúdo
        int popupTipoWidth = 340;
        int popupTipoHeight = checkBoxTipo.getPreferredSize().height + 8; // margem extra opcional

        popupMenuTipo.setPreferredSize(new Dimension(popupTipoWidth, popupTipoHeight));

        popupMenuTipo.setBackground(corFundoSubMenu);

        comboButtonTipo = new RoundedButton("Tipo", 20);
        comboButtonTipo.setFont(new Font("Georgia", Font.PLAIN, 35));
        comboButtonTipo.setBackground(corFundoComponentes);
        comboButtonTipo.setForeground(Color.BLACK);
        comboButtonTipo.setHorizontalAlignment(SwingConstants.CENTER);

        boolean[] isMenuTipoVisible = {false};

        comboButtonTipo.addActionListener(e -> {
            if (isMenuTipoVisible[0]) {
                popupMenuTipo.setVisible(false);
                isMenuTipoVisible[0] = false;
            } else {
                popupMenuTipo.setPreferredSize(new Dimension(comboButtonTipo.getWidth(), popupMenuTipo.getPreferredSize().height));
                popupMenuTipo.show(comboButtonTipo, 0, comboButtonTipo.getHeight());
                isMenuTipoVisible[0] = true;
            }
        });

        // Atualizar texto do botão com os tipos selecionados
        for (JCheckBox cb : checkBoxesTipo) {
            cb.addItemListener(e -> {
                StringBuilder sb = new StringBuilder();
                for (JCheckBox c : checkBoxesTipo) {
                    if (c.isSelected()) {
                        sb.append(c.getText()).append(", ");
                    }
                }
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 2); // remove ", "
                    comboButtonTipo.setText(sb.toString());
                } else {
                    comboButtonTipo.setText("Tipo");
                }
            });
        }


        comboButtonTipo.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                popupMenuTipo.setPreferredSize(new Dimension(comboButtonTipo.getWidth(), popupMenuTipo.getPreferredSize().height));
            }
        });

        // ---------------------------- JCheckBox Tipo -------------------------------


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
                return new JButton(new ArrowIcon(comboBox)) {{
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
                    comboBoxEstado.setFont(new Font("Georgia", Font.PLAIN, 35));
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
        comboBoxEstado.setBackground(corFundoComponentes);


        comboBoxEstado.setEditable(false);
        // -------------------- ComboBox Estado --------------------------


        // ------------ CAIXA TEXTO 'preco da compra' -----------------
        precoCompraField = new RoundedTextField(1,20);
        precoCompraField.setText(placeholder);
        precoCompraField.setForeground(corFonte);
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
        // ------------ CAIXA TEXTO 'preco da compra' -----------------


        //----------------- BOTAO ADICIONAR -------------
        adicionarButton = new RoundedButton("Adicionar", 20);
        adicionarButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        adicionarButton.setBackground(corFundoLabel);
        adicionarButton.setForeground(corFontePreto); // texto
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validação dos campos obrigatórios
                if (imagemCarregada == null ||  // verifica se foi carregada imagem
                        nomeFilme.getText().equals("Nome filme") ||
                        duracaoFilme.getText().equals("Duração (minutos)") ||
                        comboButtonIdioma.getText().equals("Idioma") ||
                        comboBoxIdade.getSelectedItem() == null || comboBoxIdade.getSelectedItem().toString().equals("Idade") ||
                        comboButtonGenero.getText().equals("Género") ||
                        comboButtonTipo.getText().equals("Tipo") ||
                        comboBoxEstado.getSelectedItem() == null || comboBoxEstado.getSelectedItem().toString().equals("Estado") ||
                        precoCompraField.getText().equals("Preço da compra(€)")) {

                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                try {
                    // Criar novo objeto Filme
                    String nome = nomeFilme.getText().trim();
                    int duracao = Integer.parseInt(duracaoFilme.getText().trim());
                    String foto = nomeFicheiroImagem; // assumimos que isto foi guardado no momento do upload

                    // Criar listas com os enums/tipos selecionados
                    LinkedList<Idioma> idiomas = new LinkedList<>();
                    for (JCheckBox checkBox : checkBoxes) {
                        if (checkBox.isSelected()) {
                            if (checkBox.getText().contains("VP")) {
                                idiomas.add(Idioma.VP);
                            } else if (checkBox.getText().contains("VO")) {
                                idiomas.add(Idioma.VO);
                            }
                        }
                    }


                    String idade = comboBoxIdade.getSelectedItem().toString().trim();

                    LinkedList<Genero> generos = new LinkedList<>();
                    for (JCheckBox checkBox : checkBoxesGenero) {
                        if (checkBox.isSelected()) {
                            String texto = checkBox.getText(); // Ex: "Ficção Científica"
                            String normalizado = NormalizarEnum.normalizarEnum(texto); // Ex: "FICAO_CIENTIFICA"
                            generos.add(Genero.valueOf(normalizado));
                        }
                    }


                    LinkedList<String> tipos = new LinkedList<>();
                    for (JCheckBox checkBox : checkBoxesTipo) {
                        if (checkBox.isSelected()) {
                            tipos.add(checkBox.getText().trim());
                        }
                    }


                    Estado estado = Estado.valueOf(comboBoxEstado.getSelectedItem().toString().trim().toUpperCase());

                    String precoTexto = precoCompraField.getText().trim()
                            .replace("€", "")
                            .replace(",", ".")
                            .replaceAll("\\s+", ""); // remove espaços

                    float precoCompra;
                    try {
                        precoCompra = Float.parseFloat(precoTexto);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Formato inválido nos campos numéricos", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    bd = BaseDados.getInstance();

                    // -------- Verificar se o filme já existe na base de dados ----------
                    boolean algumDuplicado = false;
                    for (Idioma idioma : idiomas) {
                        for (String tipo : tipos) {
                            Filme novoFilme = new Filme(
                                    nome,
                                    duracao,
                                    foto,
                                    idioma,
                                    idade,
                                    generos,
                                    tipo,
                                    estado,
                                    precoCompra
                            );

                            boolean jaExiste = false;
                            for (Filme f : bd.getFilmes()) {
                                if (
                                        NormalizarTexto.normalizar(f.getNome()).equals(NormalizarTexto.normalizar(novoFilme.getNome())) &&
                                                f.getDuracao() == novoFilme.getDuracao() &&
                                                f.getFoto().equals(novoFilme.getFoto()) &&
                                                f.getIdiomas().equals(novoFilme.getIdiomas()) &&
                                                f.getIdade().equals(novoFilme.getIdade()) &&
                                                f.getGeneros().equals(novoFilme.getGeneros()) &&
                                                f.getTipos().equals(novoFilme.getTipos()) &&
                                                f.getEstado().equals(novoFilme.getEstado()) &&
                                                Float.compare(f.getPrecoCompra(), novoFilme.getPrecoCompra()) == 0
                                ) {
                                    jaExiste = true;
                                    break;
                                }
                            }

                            if (jaExiste) {
                                algumDuplicado = true;
                                continue;
                            }

                            bd.adicionarFilme(novoFilme);
                        }
                    }

                    // ------------- Verificar se o filme já existe na base de dados ---------

                    bd.gravarDados();

                    // ------ debug -----
                    BaseDados bdVerificacao = BaseDados.carregarDados();
                    if (bdVerificacao != null) {
                        System.out.println("Filmes guardados:");
                        for (Filme f : bdVerificacao.getFilmes()) {
                            System.out.println("- " + f.getNome() + " (" + f.getDuracao() + " min, idioma( " + f.getIdiomas() + " ), idade( " + f.getIdade() + " ), generos ( " + f.getGeneros() + " ), tipos ( " + f.getTipos() + " ), estado( " + f.getEstado() + " ), preço ( " + f.getPrecoCompra() + " €), cartaz ( " + f.getFoto() + " )");
                        }
                    } else {
                        System.out.println("Erro: ficheiro de base de dados não foi carregado.");
                    }

                    if (algumDuplicado) {
                        JOptionPane.showMessageDialog(null, "Alguns filmes não foram adicionados porque já existem.", "Filmes duplicados", JOptionPane.WARNING_MESSAGE);
                    }

                    // ------ debug -----


                    // ----- Guardar imagem na raiz do projeto ----
                    String nomeFicheiroFinal = ficheiroImagemOriginal.getName();
                    File destino = new File("src/main/resources/imagens/cartazes/" + nomeFicheiroFinal);
                    destino.getParentFile().mkdirs(); // cria a pasta se ainda não existir

                    if (ficheiroImagemOriginal == null || !ficheiroImagemOriginal.exists()) {
                        JOptionPane.showMessageDialog(null, "Erro: o ficheiro de imagem original não está acessível.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        Files.copy(ficheiroImagemOriginal.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Imagem copiada para: " + destino.getAbsolutePath());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao copiar a imagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                        return;
                    }
                    //--debug--
                    System.out.println("Ficheiro carregado: " + ficheiroImagemOriginal.getAbsolutePath());
                    System.out.println("Existe? " + ficheiroImagemOriginal.exists());
                    //--debug--

                    // ----- Guardar imagem na raiz do projeto ----


                    // Redirecionar para ConfirmacaoAdicaoFilme
                    app.mostrarConfirmacaoAdicaoFilmes();


                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato inválido nos campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao guardar os dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        //----------------- BOTAO ADICIONAR -------------

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(filmesLabel, "x 450, y 110");
        mainPanel.add(adicionarButton, "x 1100, y 205, w 70, h 30");
        mainPanel.add(uploadButton, "x 100, y 290, w 110, h 410");
        mainPanel.add(nomeFilme, "x 450, y 290, w 700, h 50");
        mainPanel.add(duracaoFilme, "x 450, y 380, w 340, h 50");
        mainPanel.add(erroDuracaoLabel, "x 490, y 425, w 100, h 40");
        mainPanel.add(comboButtonIdioma, "x 810, y 380, w 340, h 50");
        mainPanel.add(comboBoxIdade, "x 450, y 470, w 340, h 50");
        mainPanel.add(comboButtonGenero, "x 810, y 470, w 340, h 50");
        mainPanel.add(comboButtonTipo, "x 450, y 560, w 340, h 50");
        mainPanel.add(comboBoxEstado, "x 810, y 560, w 340, h 50");
        mainPanel.add(precoCompraField, "x 450, y 650, w 700, h 50");
        mainPanel.add(erroPrecoCompraLabel, "x 490, y 695, w 100, h 40");

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

