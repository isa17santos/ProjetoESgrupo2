import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AdicionarProdutoAdmin {
    private final AppWindow app;
    private JPanel mainPanel;
    private JLabel logoLabel;
    private JLabel voltaLabel;
    private JLabel titleLabel;
    private JLabel produtoAdicionar;
    private JLabel erroStockLabel;
    private JLabel erroPrecoCompra;
    private JLabel erroPrecoVenda;

    private JButton adicionarButton;
    private JButton uploadButton;
    private JTextField nomeProduto;
    private JComboBox comboBoxTipo;
    private JComboBox comboBoxEstado;
    private JTextField stockField;
    private JTextField precoCompraField;
    private JTextField precoVendaField;

    private BaseDados bd;
    private BufferedImage imagemCarregada = null;
    private String nomeFicheiroImagem = null;
    private File ficheiroImagemOriginal = null;
    private final String placeholder = "Stock";
    private final String placeholderPrecoCompra = "Preço da compra (€) por unidade";
    private final String placeholderPrecoVenda = "Preço de venda (€) por unidade";


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

    public AdicionarProdutoAdmin(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

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
        titleLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        titleLabel.setOpaque(true);
        // --------------------- ADMIN LABEL -----------------------

        // --------------------- ADICIONAR PRODUTO LABEL -----------------------
        produtoAdicionar.setHorizontalAlignment(SwingConstants.CENTER);
        produtoAdicionar.setForeground(corFundoLabel);
        produtoAdicionar.setBackground(corFundo);
        produtoAdicionar.setFont(new Font("Georgia", Font.PLAIN, 80));
        produtoAdicionar.setOpaque(true);

        // --------------------- ADICIONAR PRODUTO LABEL -----------------------

        //---------------------------- BOTAO UPLOAD ------------------------------
        uploadButton = new JButton("Carregar Foto");
        uploadButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        uploadButton.setBackground(corFundoUploadButton);
        uploadButton.setForeground(corFontePreto);
        uploadButton.setFocusPainted(false);
        uploadButton.setContentAreaFilled(true);
        uploadButton.setBorderPainted(false);
        uploadButton.setOpaque(true);
        uploadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        // --------------- CAIXA DE TEXTO NOME PRODUTO --------------
        nomeProduto = new RoundedTextField(1,20);
        nomeProduto.setHorizontalAlignment(SwingConstants.CENTER);
        nomeProduto.setBackground(corFundoComponentes);
        nomeProduto.setFont(new Font("Georgia", Font.PLAIN, 35));
        nomeProduto.setText("Nome produto");
        nomeProduto.setForeground(corFonte); // texto
        nomeProduto.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nomeProduto.getText().equals("Nome produto")) {
                    nomeProduto.setText("");
                    nomeProduto.setForeground(corFontePreto); // Cor real do texto do utilizador
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nomeProduto.getText().isEmpty()) {
                    nomeProduto.setText("Nome produto");
                    nomeProduto.setForeground(corFonte); // Placeholder de novo
                }
            }
        });

        // -------------------- ComboBox tipo --------------------------
        String[] opcoes = {"Bebida", "Aperitivo", "Pack"};
        comboBoxTipo = new RoundedComboBox<>(opcoes, 20);
        comboBoxTipo.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Não selecionar nenhum item no início → mostra placeholder
        comboBoxTipo.setSelectedItem(null);

        comboBoxTipo.setUI(new BasicComboBoxUI() {
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
        comboBoxTipo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Tipo");
                    label.setForeground(corFonte);
                    comboBoxTipo.setFont(new Font("Georgia", Font.PLAIN, 35));
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

        comboBoxTipo.setFont(new Font("Georgia", Font.PLAIN, 25));
        comboBoxTipo.setForeground(corFontePreto);
        comboBoxTipo.setBackground(corFundoComponentes);

        comboBoxTipo.setEditable(false);
        // -------------------- ComboBox tipo --------------------------

        // -------------------- ComboBox Estado --------------------------
        String[] opcoesEstado = {"Ativo", "Inativo"};
        comboBoxEstado = new RoundedComboBox<>(opcoesEstado, 20);

        // Não selecionar nenhum item no início → mostra placeholder
        comboBoxEstado.setSelectedItem(null);
        comboBoxEstado.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        comboBoxEstado.setFont(new Font("Georgia", Font.PLAIN, 35));
        comboBoxEstado.setForeground(corFontePreto);
        comboBoxEstado.setBackground(corFundoComponentes);


        comboBoxEstado.setEditable(false);
        // -------------------- ComboBox Estado --------------------------

        // ------------ CAIXA TEXTO 'stock' -----------------
        stockField = new RoundedTextField(1,20);
        stockField.setText(placeholder);
        stockField.setForeground(corFonte);
        stockField.setHorizontalAlignment(SwingConstants.CENTER);
        stockField.setBackground(corFundoComponentes);
        stockField.setFont(new Font("Georgia", Font.PLAIN, 35));
        stockField.setCursor(new Cursor(Cursor.HAND_CURSOR));

        erroStockLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        erroStockLabel.setText("");
        erroStockLabel.setForeground(Color.RED);
        erroStockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        erroStockLabel.setVisible(false);

        stockField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (stockField.getText().equals(placeholder)) {
                    stockField.setText("");
                    stockField.setForeground(corFontePreto);
                }
            }

            //validacao de stock - importante saber
            @Override
            public void focusLost(FocusEvent e) {

                String textoBruto = stockField.getText().trim();
                String textoLimpo = textoBruto.replaceAll("[^\\d.-]", "");

                //texto está vazio, ou se tem um hifen ou se tem um ponto
                if (textoLimpo.isEmpty() || textoLimpo.equals("-") || textoLimpo.equals(".")) {
                    erroStockLabel.setText("Insira um valor válido.");
                    erroStockLabel.setVisible(true);
                    stockField.setText(placeholder);
                    stockField.setForeground(corFonte);
                    return;
                }

                try {
                    //verifica se é um valor inteiro
                    int valor = Integer.parseInt(textoLimpo);
                    if (valor <= 0) {
                        erroStockLabel.setText("Insira um valor superior a 0.");
                        erroStockLabel.setVisible(true);
                        stockField.setText(placeholder);
                        stockField.setForeground(corFonte);
                    } else {
                        stockField.setText(textoLimpo);
                        stockField.setForeground(corFontePreto);
                        erroStockLabel.setVisible(false);
                    }
                } catch (NumberFormatException ex) {
                    stockField.setText("Insira um valor válido.");
                    stockField.setVisible(true);
                    erroStockLabel.setText(placeholder);
                    erroStockLabel.setForeground(corFonte);
                }
            }
        });
        // ------------ CAIXA TEXTO 'stock' -----------------

        // ------------ CAIXA TEXTO 'preco de compra' -----------------
        precoCompraField = new RoundedTextField(1,20);
        precoCompraField.setText(placeholderPrecoCompra);
        precoCompraField.setForeground(corFonte);
        precoCompraField.setHorizontalAlignment(SwingConstants.CENTER);
        precoCompraField.setBackground(corFundoComponentes);
        precoCompraField.setFont(new Font("Georgia", Font.PLAIN, 35));

        erroPrecoCompra.setFont(new Font("Georgia", Font.PLAIN, 18));
        erroPrecoCompra.setText("");
        erroPrecoCompra.setForeground(Color.RED);
        erroPrecoCompra.setHorizontalAlignment(SwingConstants.CENTER);
        erroPrecoCompra.setVisible(false);

        precoCompraField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (precoCompraField.getText().equals(placeholderPrecoCompra)) {
                    precoCompraField.setText("");
                    precoCompraField.setForeground(corFontePreto);
                }
            }

            //importante saber como funciona
            @Override
            public void focusLost(FocusEvent e) {
                String textoBruto = precoCompraField.getText().trim();
                String textoLimpo = textoBruto.replace("€", "").replace(",", ".").replaceAll("[^\\d.-]", "");

                if (textoLimpo.isEmpty() || textoLimpo.equals("-") || textoLimpo.equals(".")) {
                    erroPrecoCompra.setText("Insira um valor válido em euros.");
                    erroPrecoCompra.setVisible(true);
                    precoCompraField.setText(placeholderPrecoCompra);
                    precoCompraField.setForeground(corFonte);
                    return;
                }

                try {
                    double valor = Double.parseDouble(textoLimpo);
                    if (valor <= 0) {
                        erroPrecoCompra.setText("Insira um valor superior a 0 em euros.");
                        erroPrecoCompra.setVisible(true);
                        precoCompraField.setText(placeholderPrecoCompra);
                        precoCompraField.setForeground(corFonte);
                    } else {
                        String valorFormatado = String.format("%.2f €", valor).replace(".", ",");
                        precoCompraField.setText(valorFormatado);
                        precoCompraField.setForeground(corFontePreto);
                        erroPrecoCompra.setVisible(false);
                    }
                } catch (NumberFormatException ex) {
                    erroPrecoCompra.setText("Insira um valor válido em euros.");
                    erroPrecoCompra.setVisible(true);
                    precoCompraField.setText(placeholderPrecoCompra);
                    precoCompraField.setForeground(corFonte);
                }
            }
        });
        // ------------ CAIXA TEXTO 'preco de compra' -----------------

        // ------------ CAIXA TEXTO 'preco de venda' -----------------
        precoVendaField = new RoundedTextField(1,20);
        precoVendaField.setText(placeholderPrecoVenda);
        precoVendaField.setForeground(corFonte);
        precoVendaField.setHorizontalAlignment(SwingConstants.CENTER);
        precoVendaField.setBackground(corFundoComponentes);
        precoVendaField.setFont(new Font("Georgia", Font.PLAIN, 35));

        erroPrecoVenda.setFont(new Font("Georgia", Font.PLAIN, 18));
        erroPrecoVenda.setText("");
        erroPrecoVenda.setForeground(Color.RED);
        erroPrecoVenda.setHorizontalAlignment(SwingConstants.CENTER);
        erroPrecoVenda.setVisible(false);

        precoVendaField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (precoVendaField.getText().equals(placeholderPrecoVenda)) {
                    precoVendaField.setText("");
                    precoVendaField.setForeground(corFontePreto);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String textoBruto = precoVendaField.getText().trim();
                String textoLimpo = textoBruto.replace("€", "").replace(",", ".").replaceAll("[^\\d.-]", "");

                if (textoLimpo.isEmpty() || textoLimpo.equals("-") || textoLimpo.equals(".")) {
                    erroPrecoVenda.setText("Insira um valor válido em euros.");
                    erroPrecoVenda.setVisible(true);
                    precoVendaField.setText(placeholderPrecoVenda);
                    precoVendaField.setForeground(corFonte);
                    return;
                }

                try {
                    double valor = Double.parseDouble(textoLimpo);
                    if (valor <= 0) {
                        erroPrecoVenda.setText("Insira um valor superior a 0 em euros.");
                        erroPrecoVenda.setVisible(true);
                        precoVendaField.setText(placeholderPrecoVenda);
                        precoVendaField.setForeground(corFonte);
                    } else {
                        String valorFormatado = String.format("%.2f €", valor).replace(".", ",");
                        precoVendaField.setText(valorFormatado);
                        precoVendaField.setForeground(corFontePreto);
                        erroPrecoVenda.setVisible(false);
                    }
                } catch (NumberFormatException ex) {
                    erroPrecoVenda.setText("Insira um valor válido em euros.");
                    erroPrecoVenda.setVisible(true);
                    precoVendaField.setText(placeholderPrecoVenda);
                    precoVendaField.setForeground(corFonte);
                }
            }
        });
        // ------------ CAIXA TEXTO 'preco de venda' -----------------

        //----------------- BOTAO ADICIONAR -------------
        adicionarButton = new RoundedButton("Adicionar", 20);
        adicionarButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        adicionarButton.setBackground(corFundoLabel);
        adicionarButton.setForeground(corFontePreto); // texto
        adicionarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //importante saber
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validação dos campos obrigatórios
                if (imagemCarregada == null ||  // verifica se foi carregada imagem
                        nomeProduto.getText().equals("Nome produto") ||
                        stockField.getText().equals("Stock") ||
                        comboBoxTipo.getSelectedItem() == null || comboBoxTipo.getSelectedItem().toString().equals("Tipo") ||
                        comboBoxEstado.getSelectedItem() == null || comboBoxEstado.getSelectedItem().toString().equals("Estado") ||
                        precoCompraField.getText().equals("Preço da compra (€) por unidade") ||
                        precoVendaField.getText().equals("Preço de venda (€) por unidade")) {

                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    //criar novo produto
                    String nome = nomeProduto.getText().trim();
                    int stock = Integer.parseInt(stockField.getText().trim());
                    String foto = nomeFicheiroImagem;
                    TipoProduto tipoProduto = TipoProduto.valueOf(comboBoxTipo.getSelectedItem().toString().trim().toUpperCase());
                    Estado estado = Estado.valueOf(comboBoxEstado.getSelectedItem().toString().trim().toUpperCase());

                    String precoCompraTexto = precoCompraField.getText().trim()
                            .replace("€", "")
                            .replace(",", ".")
                            .replaceAll("\\s+", "");

                    //aqui retira o símbolo do euro e troca as virgulas por ponto
                    String precoVendaTexto = precoVendaField.getText().trim()
                            .replace("€", "")
                            .replace(",", ".")
                            .replaceAll("\\s+", "");

                    float precoCompra, precoVenda;
                    try {
                        precoCompra = Float.parseFloat(precoCompraTexto);
                        precoVenda = Float.parseFloat(precoVendaTexto);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Formato inválido nos campos numéricos", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    //abre a base de dados
                    bd = BaseDados.getInstance();

                    // -------- Verificar se o produto já existe na base de dados ----------

                    //o ultimo indentificador unico, id + 1
                    Integer lastId = bd.getLastIdProduto() + 1;
                    //cria um novo produto, mas ainda não guarda na base de dados
                    Produto novoProduto = new Produto(lastId, foto, nome, tipoProduto, estado, stock, precoCompra, precoVenda);


                    boolean jaExiste = false;
                    for (Produto produto : bd.getProdutos()) {
                        if (NormalizarTexto.normalizar(produto.getNome()).equals(NormalizarTexto.normalizar(novoProduto.getNome())) &&
                                produto.getStock() == novoProduto.getStock() &&
                                produto.getFoto().equals(novoProduto.getFoto()) &&
                                produto.getTipoProduto().equals(novoProduto.getTipoProduto()) &&
                                produto.getEstado().equals(novoProduto.getEstado()) &&
                                Float.compare(produto.getPrecoCompraUnidade(), novoProduto.getPrecoCompraUnidade()) == 0 &&
                                Float.compare(produto.getPrecoVendaUnidade(), novoProduto.getPrecoVendaUnidade()) == 0
                        ) {
                            jaExiste = true;
                            break;
                        }
                    }

                    if (jaExiste) {
                        JOptionPane.showMessageDialog(null, "Produto já existe", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    bd.adicionarProduto(novoProduto);

                    // ------------- Verificar se o produto já existe na base de dados ---------

                    //no ficheiro basededados.dat
                    bd.gravarDados();

                    // ------ debug -----
                    BaseDados bdVerificacao = BaseDados.carregarDados();
                    if (bdVerificacao != null) {
                        System.out.println("Produtos guardados:");
                        for (Produto produto : bdVerificacao.getProdutos()) {
                            System.out.println(produto.toString());
                        }
                    } else {
                        System.out.println("Erro: ficheiro de base de dados não foi carregado.");
                    }
                    // ------ debug -----


                    // ----- Guardar imagem na raiz do projeto ----
                    String nomeFicheiroFinal = ficheiroImagemOriginal.getName();
                    File destino = new File("src/main/resources/imagens/produtos-bar/" + nomeFicheiroFinal);
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


                    // Redirecionar para
                    app.mostrarPaginaConfirmacaoAdicaoProduto();


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
        mainPanel.add(titleLabel, "x 550, y 20");
        mainPanel.add(produtoAdicionar, "x 350, y 100");
        mainPanel.add(uploadButton, "x 100, y 290, w 110, h 410");
        mainPanel.add(nomeProduto, "x 450, y 290, w 700, h 50");
        //combobox tipo
        mainPanel.add(comboBoxTipo, "x 455, y 375, w 335, h 50");
        mainPanel.add(comboBoxEstado, "x 810, y 375, w 335, h 50");
        //stock
        mainPanel.add(stockField, "x 450, y 465, w 700, h 50");
        mainPanel.add(erroStockLabel, "x 490, y 515, w 100, h 40");
        //preco compra
        mainPanel.add(precoCompraField, "x 450, y 555, w 700, h 50");
        mainPanel.add(erroPrecoCompra, "x 490, y 600, w 100, h 40");
        //preco venda
        mainPanel.add(precoVendaField, "x 450, y 650, w 700, h 50");
        mainPanel.add(erroPrecoVenda, "x 490, y 695, w 100, h 40");

        //adicionar button
        mainPanel.add(adicionarButton, "x 1100, y 205, w 70, h 30");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Redireciona para a página principal do bar
                app.mostrarPaginaPrincipalProdutosBarAdmin();
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
