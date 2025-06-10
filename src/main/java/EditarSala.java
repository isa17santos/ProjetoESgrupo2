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

public class EditarSala {
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel salasLabel = new JLabel("Salas - Editar");
    private JTextField nomeSala = new JTextField(20);
    private JTextField numeroFilas = new JTextField(20);
    private JTextField numeroLugaresFila = new JTextField(20);
    private JComboBox<Object> comboBoxEcra = new JComboBox<>();
    private JComboBox<Object> comboBoxAcessibilidade = new JComboBox<>();
    private JComboBox <Object> comboBoxTipo = new JComboBox<>();
    private JComboBox<Object> comboBoxEstado = new JComboBox<>();
    private JButton editarButton = new JButton("Editar");
    private JLabel erroNumeroFilas = new JLabel();
    private JLabel erroNumeroLugaresFila = new JLabel();
    private Sala salaAEditar = null;
    private BaseDados bd = BaseDados.getInstance();

    private final AppWindow app;

    private String input = null;

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
    public EditarSala(AppWindow app, String nomeSala) {
        this.app = app;
        configurarComponentes(nomeSala);
    }


    private void configurarComponentes(String nomeSalaValue) {

        // Obter a sala a editar
        salaAEditar = BaseDados.getInstance().getSalaByNome(nomeSalaValue);

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
        nomeSala.setText(salaAEditar.getDesignacao());            // Substituir por Dados da sala
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
        
        // Erro label para mostrar mensagens de erro
        erroNumeroFilas.setFont(new Font("Georgia", Font.PLAIN, 18));
        erroNumeroFilas.setText("");
        erroNumeroFilas.setBackground(corFundo);
        erroNumeroFilas.setForeground(Color.RED);
        erroNumeroFilas.setHorizontalAlignment(SwingConstants.CENTER);
        erroNumeroFilas.setVisible(false);

        // Substituir por Dados da sala
        numeroFilas.setText(String.valueOf(salaAEditar.getNumFilas()));

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
                        erroNumeroFilas.setText("Insira um valor superior a 0.");
                        erroNumeroFilas.setVisible(true);
                        numeroFilas.setText("Nº Filas");
                        numeroFilas.setForeground(corFonte);
                    } else {
                        erroNumeroFilas.setVisible(false); // valor válido
                    }
                } catch (NumberFormatException ex) {
                    erroNumeroFilas.setText("Insira um valor numérico válido.");
                    erroNumeroFilas.setVisible(true);
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
        
        // Erro label para mostrar mensagens de erro
        erroNumeroLugaresFila.setFont(new Font("Georgia", Font.PLAIN, 18));
        erroNumeroLugaresFila.setText("");
        erroNumeroLugaresFila.setBackground(corFundo);
        erroNumeroLugaresFila.setForeground(Color.RED);
        erroNumeroLugaresFila.setHorizontalAlignment(SwingConstants.CENTER);
        erroNumeroLugaresFila.setVisible(false);

        // Substituir por Dados da sala
        numeroLugaresFila.setText(String.valueOf(salaAEditar.getNumLugaresFila()));

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
                        erroNumeroLugaresFila.setText("Insira um valor superior a 0.");
                        erroNumeroLugaresFila.setVisible(true);
                        numeroLugaresFila.setText("Nº Lugares por Fila");
                        numeroLugaresFila.setForeground(corFonte);
                    } else {
                        erroNumeroLugaresFila.setVisible(false); // valor válido
                    }
                } catch (NumberFormatException ex) {
                    erroNumeroLugaresFila.setText("Insira um valor numérico válido.");
                    erroNumeroLugaresFila.setVisible(true);
                    numeroLugaresFila.setText("Nº Lugares por Fila");
                    numeroLugaresFila.setForeground(corFonte);
                }
            }
        });
        // --------------- CAIXA DE TEXTO NÚMERO DE LUGARES POR FILA --------------

        // -------------------- COMBOBOX ECRÃ --------------------------
        String[] opcoesEcra = {"10x5m", "14x6m", "22x16m", "30x23m"};
        comboBoxEcra = new RoundedComboBox<>(opcoesEcra, 20);

        // Mudar o valor do placeholder para o valor atual da sala
        comboBoxEcra.setSelectedItem(salaAEditar.getEcra());

        comboBoxEcra.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBoxEcra) {
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
                return new JButton(new ArrowIcon(comboBoxEcra)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        // Custom renderer com placeholder
        comboBoxEcra.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Ecrã");
                    label.setForeground(corFonte);
                    comboBoxEcra.setFont(new Font("Georgia", Font.PLAIN, 35));
                } else {
                    label.setForeground(corFontePreto);
                    label.setText(value.toString());
                }

                if (index == -1) label.setBackground(corFundoComponentes);
                else if (isSelected) label.setBackground(corHoverComboBox);
                else label.setBackground(corFundoSubMenu);

                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                return label;
            }
        });

        comboBoxEcra.setFont(new Font("Georgia", Font.PLAIN, 35));
        comboBoxEcra.setBackground(corFundoComponentes);

        comboBoxEcra.setEditable(false);
        // -------------------- COMBOX ECRÃ --------------------------

        // -------------------- COMBOBOX ACESSIBILIDADE --------------------------

        String[] opcoesAcessibilidade = {"Sim", "Não"};
        comboBoxAcessibilidade = new RoundedComboBox<>(opcoesAcessibilidade, 20);

        // Mudar o valor do placeholder para o valor atual da sala
        if (salaAEditar.getAcessibilidade() == Acessibilidade.SIM) {
            comboBoxAcessibilidade.setSelectedItem("Sim");
        } else {
            comboBoxAcessibilidade.setSelectedItem("Não");
        }
        comboBoxAcessibilidade.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBoxAcessibilidade) {
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
                return new JButton(new ArrowIcon(comboBoxAcessibilidade)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        // Custom renderer com placeholder
        comboBoxAcessibilidade.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Acessibilidade");
                    label.setForeground(corFonte);
                    comboBoxAcessibilidade.setFont(new Font("Georgia", Font.PLAIN, 35));
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
        comboBoxAcessibilidade.setFont(new Font("Georgia", Font.PLAIN, 35));
        comboBoxAcessibilidade.setBackground(corFundoComponentes);
        comboBoxAcessibilidade.setEditable(false);
        // -------------------- COMBOBOX ACESSIBILIDADE --------------------------


        // -------------------- COMBOBOX TIPO --------------------------
        String[] opcoesTipo = {"Normal", "VIP", "5D"};
        comboBoxTipo = new RoundedComboBox<>(opcoesTipo, 20);

        // Mudar o valor do placeholder para o valor atual da sala
        comboBoxTipo.setSelectedItem(salaAEditar.getTipo());

        // Não selecionar nenhum item no início → mostra placeholder
        comboBoxTipo.setSelectedItem(null);
        comboBoxTipo.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBoxTipo) {
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
                return new JButton(new ArrowIcon(comboBoxTipo)) {{
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
                    label.setText(salaAEditar.getTipo());
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
        comboBoxTipo.setFont(new Font("Georgia", Font.PLAIN, 35));
        comboBoxTipo.setBackground(corFundoComponentes);
        comboBoxTipo.setEditable(false);
        // -------------------- COMBOBOX TIPO --------------------------

        // -------------------- COMBOBOX ESTADO --------------------------
        String[] opcoesEstado = {"Ativo", "Inativo"};
        comboBoxEstado = new RoundedComboBox<>(opcoesEstado, 20);

        // Mudar o valor do placeholder para o valor atual da sala
        if (salaAEditar.getEstado() == Estado.ATIVO) {
            comboBoxEstado.setSelectedItem("Ativo");
        } else {
            comboBoxEstado.setSelectedItem("Inativo");
        }

        comboBoxEstado.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBoxEstado) {
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
                return new JButton(new ArrowIcon(comboBoxEstado)) {{
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
                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    String estado = salaAEditar.getEstado().toString();
                    if (!estado.isEmpty()) {
                        label.setText(estado.substring(0, 1).toUpperCase() + estado.substring(1).toLowerCase());
                    } else {
                        label.setText("Estado");
                    }
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
        comboBoxEstado.setBackground(corFundoComponentes);
        comboBoxEstado.setEditable(false);
        // -------------------- COMBOBOX ESTADO --------------------------

        //----------------- BOTAO EDITAR -------------
        editarButton = new RoundedButton("Editar", 20);
        editarButton.setFont(new Font("Georgia", Font.PLAIN, 40));
        editarButton.setBackground(corFundoLabel);
        editarButton.setForeground(corFontePreto); // texto
        //----------------- BOTAO EDITAR -------------

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(salasLabel, "x 450, y 110");
        mainPanel.add(nomeSala, "x 250, y 250, w 800, h 50");
        mainPanel.add(numeroFilas, "x 250, y 350, w 350, h 50");
        mainPanel.add(numeroLugaresFila, "x 700, y 350, w 350, h 50");
        mainPanel.add(comboBoxEcra, "x 250, y 450, w 350, h 50");
        mainPanel.add(comboBoxAcessibilidade, "x 700, y 450, w 350, h 50");
        mainPanel.add(comboBoxTipo, "x 250, y 550, w 350, h 50");
        mainPanel.add(comboBoxEstado, "x 700, y 550, w 350, h 50");
        mainPanel.add(editarButton, "x 250, y 650, w 800, h 50");
        mainPanel.add(erroNumeroFilas, "x 250, y 400, w 350, h 30");
        mainPanel.add(erroNumeroLugaresFila, "x 700, y 400, w 350, h 30");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaPrincipalSalasAdmin();
            }
        });

        // Redirecionar para ConfirmarCriaçãoSala
        editarButton.addActionListener(e -> {
            String nome = nomeSala.getText().trim();
            String filas = numeroFilas.getText().trim();
            String lugares = numeroLugaresFila.getText().trim();
            String ecra = comboBoxEcra.getSelectedItem().toString().trim();
            String acessibilidade = comboBoxAcessibilidade.getSelectedItem().toString().trim();
            String tipo = comboBoxTipo.getSelectedItem().toString().trim();
            String estado = comboBoxEstado.getSelectedItem().toString().trim();


            // VALIDAÇÃO DOS CAMPOS
            if (nome.isEmpty() || nome.equals("Designação") || filas.isEmpty() || filas.equals("Nº Filas")
                               || lugares.isEmpty() || lugares.equals("Nº Lugares por Fila") || ecra == null
                               || acessibilidade == null || tipo == null || estado == null) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    // Obter a sala a editar
                    int numFilas = Integer.parseInt(filas);
                    int numLugaresFila = Integer.parseInt(lugares);

                    // Transforma a acessibilidade e estado em enums
                    Acessibilidade acessibilidadeEnum;
                    if (acessibilidade.equalsIgnoreCase("Sim")) {
                        acessibilidadeEnum = Acessibilidade.SIM;
                    } else if (acessibilidade.equalsIgnoreCase("Não")) {
                        acessibilidadeEnum = Acessibilidade.NAO;
                    } else {
                        throw new IllegalArgumentException("Valor de acessibilidade inválido: " + acessibilidade);
                    }

                    // Transformar o estado em enum
                    Estado estadoEnum;
                    if (estado.equalsIgnoreCase("Ativo")) {
                        estadoEnum = Estado.ATIVO;
                    } else if (estado.equalsIgnoreCase("Inativo")) {
                        estadoEnum = Estado.INATIVO;
                    } else {
                        throw new IllegalArgumentException("Invalid estado value: " + estado);
                    }

                    // Criar a nova sala com os dados editados
                    Sala salaEditada = new Sala(nome, numFilas, numLugaresFila, ecra, acessibilidadeEnum, tipo, estadoEnum);

                    // -------- Verificar se já existe outra sala com o mesmo nome na base de dados ----------
                    boolean salaDuplicada = false;
                    for (Sala sala : bd.getSalas()) {

                        // Passa a sala que está a ser editada
                        if (sala.equals(salaAEditar)) continue;

                        // Verifica se existe outra sala com o mesmo nome
                        if (sala.getDesignacao().equalsIgnoreCase(salaEditada.getDesignacao())) {
                            salaDuplicada = true;
                            break;
                        }
                    }
                    if (salaDuplicada) {
                        JOptionPane.showMessageDialog(null, "A sala já existe na base de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else{
                        // Atualizar a sala na base de dados
                        bd.editarSala(salaAEditar, salaEditada);

                        // Gravar a base de dados
                        bd.gravarDados();

                        // Redirecionar para ConfirmarEdicaoSala
                        app.mostrarConfirmarEdicaoSala(nome);
                    }

                    // ------ debug -----

                    BaseDados bdVerificacao = BaseDados.carregarDados();
                    List<Sala> salas = bdVerificacao.getSalas();
                    if (bdVerificacao != null) {
                        System.out.println("Base de dados carregada com sucesso.");
                        for (Sala sala : salas) {
                            if (sala.equals(salaEditada)) {
                                System.out.println("Sala editada com sucesso: " + sala);
                            }
                        }
                    } else {
                        System.out.println("Erro ao carregar a base de dados.");
                    }

                    // ------ debug -----

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato inválido nos campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao guardar os dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}


