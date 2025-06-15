import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Bilheteira {
    private JPanel mainPanel;
    private final AppWindow app;

    private JLabel logoLabel = new JLabel();;
    private JLabel voltaLabel = new JLabel();;
    private JLabel bilheteiraLabel = new JLabel("Bilheteira");
    private JComboBox comboBoxSessoes;
    private JDatePickerImpl datePicker;
    private JComboBox comboBoxGenero;
    private JComboBox comboBoxIdade;
    private JComboBox salasComboBox;
    private JLabel limparFiltrosLabel;

    private JScrollPane scrollPaneCartazes;
    private JPanel cartazPanel;
    private JButton botaoFiltrar;


    private BaseDados bd;

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
    public Bilheteira(AppWindow app) {
        this.app = app;
        this.bd = BaseDados.getInstance();
        configurarComponentes();
    }

    private void configurarComponentes() {
        // obter o filme a editar
        List<Filme> filmesExistentes = bd.getFilmes(); // lista de todos os filmes existes na BD e ficheiro binario

        List<Filme> listaFilmesComSessao = new ArrayList<>();  // lista de todos os filmes como mesmo nome de 'filme'

        Set<String> nomesAdicionados = new HashSet<>();

        //todas as sessões existentes
        List<Sessao> listaSessoes = bd.getSessoes();

        for (Filme f : filmesExistentes) {
            // vai procurar todos os filmes com sessao
            if (f.isComSessao()) {
                if (!nomesAdicionados.contains(f.getNome())) {
                    listaFilmesComSessao.add(f);
                    nomesAdicionados.add(f.getNome());
                }
            }
        }

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

        // --------------------- BILHETEIRA LABEL -----------------------
        bilheteiraLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bilheteiraLabel.setForeground(corFundoLabel);
        bilheteiraLabel.setBackground(corFundo);
        bilheteiraLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        bilheteiraLabel.setOpaque(true);
        // --------------------- BILHETEIRA LABEL -----------------------

        // -------------------- ComboBox Sessoes --------------------------
        // Criar conjunto ordenado para garantir horários únicos e ordenados
        Set<String> horariosUnicos = new TreeSet<>();
        for (Sessao sessao : listaSessoes) {
            String horario = String.format("%02d:%02d", sessao.getHora(), sessao.getMinuto());
            horariosUnicos.add(horario);
        }

        // Converter para array (sem placeholder!)
        String[] horariosArray = horariosUnicos.toArray(new String[0]);

        // Criar RoundedComboBox com os horários
        comboBoxSessoes = new RoundedComboBox<>(horariosArray, 20);
        comboBoxSessoes.setSelectedItem(null); // Nenhum item selecionado → placeholder

        // Personalizar aparência do popup
        comboBoxSessoes.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBox) {
                    @Override
                    public void show() {
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

        // Renderer com placeholder "Sessões" (não está na lista!)
        comboBoxSessoes.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value == null && index == -1) {
                    label.setText("Sessões"); // Placeholder
                    label.setForeground(corFonte);
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

        comboBoxSessoes.setFont(new Font("Georgia", Font.PLAIN, 25));
        comboBoxSessoes.setForeground(corFontePreto);
        comboBoxSessoes.setBackground(corFundoComponentes);
        comboBoxSessoes.setEditable(false);
        // -------------------- ComboBox Sessoes --------------------------


        //--------------------- DataPicker ---------------------------
        // Modelo da data
        UtilDateModel model = new UtilDateModel();
        model.setSelected(false);

        // Labels personalizados
        Properties p = new Properties();
        p.put("text.today", "Hoje");
        p.put("text.month", "Mês");
        p.put("text.year", "Ano");

        // Criar o painel do calendário
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

        // Criar o componente principal
        datePicker = new JDatePickerImpl(datePanel, new JFormattedTextField.AbstractFormatter() {
            @Override
            public Object stringToValue(String text) {
                if (text == null || text.equals("Data") || text.isBlank()) return null;
                try {
                    return new SimpleDateFormat("dd-MM-yyyy").parse(text);
                } catch (ParseException e) {
                    return null;
                }
            }

            @Override
            public String valueToString(Object value) {
                if (value == null) return "Data"; // o placeholder
                if (value instanceof Calendar) {
                    Calendar cal = (Calendar) value;
                    return new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());
                }
                return "Data";
            }
        });

        // ACESSO AO CAMPO DE TEXTO INTERNO
        JFormattedTextField campoTexto = datePicker.getJFormattedTextField();

        // Alinha texto ao centro
        campoTexto.setHorizontalAlignment(JTextField.CENTER);

        // Define cor preta
        campoTexto.setForeground(Color.BLACK);

        // Define placeholder inicial visível
        campoTexto.setText("Data");

        campoTexto.setFont(new Font("Georgia", Font.PLAIN, 25));


        // Obter o tamanho ideal do calendário
        Dimension calendarPreferredSize = datePanel.getPreferredSize();
        int largura = calendarPreferredSize.width;
        int altura = 50; // Altura desejada para a caixa de texto

        // Aplicar tamanhos consistentes
        datePicker.setPreferredSize(new Dimension(largura, altura));
        datePicker.setSize(largura, altura);

        // Campo de texto
        JFormattedTextField textField = datePicker.getJFormattedTextField();
        textField.setBackground(Color.decode("#FFC133"));
        textField.setFont(new Font("Georgia", Font.PLAIN, 20));
        textField.setPreferredSize(new Dimension((int)(largura * 0.75), altura));
        textField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Botão
        JButton button = (JButton) datePicker.getComponent(1);
        button.setBackground(Color.decode("#FFC133"));
        button.setPreferredSize(new Dimension((int)(largura * 0.25), altura));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);

        // Criar painel de suporte para o botão com margem à esquerda
        JPanel buttonWrapper = new JPanel(new BorderLayout());
        buttonWrapper.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 0)); // margem à esquerda de 10px
        buttonWrapper.setBackground(corFundo);
        buttonWrapper.setOpaque(true); // para herdar fundo do datePicker
        buttonWrapper.add(button, BorderLayout.CENTER);

        // Layout interno coerente
        datePicker.setLayout(new BorderLayout());
        datePicker.add(textField, BorderLayout.CENTER);
        datePicker.add(buttonWrapper, BorderLayout.EAST);
        //--------------------- DataPicker ---------------------------

        // -------------------- ComboBox Genero --------------------------
        String[] opcoesGenero = {"Animação", "Ação", "Comédia", "Terror", "Romance", "Ficção Científica"};
        comboBoxGenero = new RoundedComboBox<>(opcoesGenero, 20);

        // Define valor inicial como null para ativar placeholder
        comboBoxGenero.setSelectedItem(null);

        comboBoxGenero.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBox) {
                    @Override
                    public void show() {
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
        comboBoxGenero.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Verifica se é o item visível (index == -1) e se não há valor selecionado
                if (value == null && index == -1) {
                    label.setText("Género");
                    label.setFont(new Font("Georgia", Font.PLAIN, 35));
                    label.setForeground(corFonte);
                } else {
                    label.setText(value != null ? value.toString() : ""); // garantir texto
                    label.setFont(new Font("Georgia", Font.PLAIN, 25));
                    label.setForeground(corFontePreto);
                }

                if (index == -1) {
                    label.setBackground(corFundoComponentes);
                } else if (isSelected) {
                    label.setBackground(corHoverComboBox);
                } else {
                    label.setBackground(corFundoSubMenu);
                }

                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                return label;
            }
        });


        comboBoxGenero.setFont(new Font("Georgia", Font.PLAIN, 25));
        comboBoxGenero.setForeground(corFontePreto);
        comboBoxGenero.setBackground(corFundoComponentes);
        comboBoxGenero.setEditable(false);
        // -------------------- ComboBox Genero --------------------------


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
                return new JButton(new AdicionarFilme.ArrowIcon(comboBox)) {{
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
                    comboBoxIdade.setFont(new Font("Georgia", Font.PLAIN, 25));
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
        comboBoxIdade.setForeground(corFontePreto);
        comboBoxIdade.setBackground(corFundoComponentes);

        comboBoxIdade.setEditable(false);
        // -------------------- ComboBox idade --------------------------

        //------------------------------- ComboBox Salas ----------------------------------
        // Dados das salas
        List<Sala> salas = bd.getSalas();

        List<String> opcoesSalas = new ArrayList<>();
        for(Sala sala : salas){
            if(!opcoesSalas.contains(sala.getTipo())){
                opcoesSalas.add(sala.getTipo());
            }
        }

        salasComboBox = new RoundedComboBox<>(opcoesSalas.toArray(new String[0]), 20);
        // Não selecionar nenhum item no início → mostra placeholder
        salasComboBox.setSelectedItem(null);
        salasComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(salasComboBox) {
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
                return new JButton(new ArrowIcon(salasComboBox)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        // Custom renderer com placeholder
        salasComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Sala");
                    label.setForeground(corFontePreto);
                    salasComboBox.setFont(new Font("Georgia", Font.PLAIN, 25));
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

        salasComboBox.setFont(new Font("Georgia", Font.PLAIN, 25));
        salasComboBox.setBackground(corFundoComponentes);
        salasComboBox.setForeground(corFontePreto);
        salasComboBox.setEditable(false);
        salasComboBox.setFocusable(false);
        //------------------------------- ComboBox Salas ----------------------------------


        //------------------------------- Cartazes filmes ----------------------------------

        // Painel para cartazes
        cartazPanel = new JPanel(new GridLayout(0, 4, 15, 15));
        cartazPanel.setBackground(corFundo);

        // ScrollPane
        scrollPaneCartazes = new JScrollPane(cartazPanel);
        scrollPaneCartazes.setBorder(null);
        scrollPaneCartazes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneCartazes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneCartazes.getVerticalScrollBar().setUnitIncrement(16);
        //------------------------------- Cartezes filmes ----------------------------------

        //Assim, mostra todos os filmes com sessão quando a página abre.
        desenharCartazes(listaFilmesComSessao);

        //----------------------- LABEL LIMPAR FILTRO -------------------------------------------
        limparFiltrosLabel = new JLabel("<html><u>Limpar filtragem</u></html>");
        limparFiltrosLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        limparFiltrosLabel.setForeground(Color.RED);
        limparFiltrosLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        limparFiltrosLabel.setVisible(false);
        limparFiltrosLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                comboBoxSessoes.setSelectedIndex(-1);
                comboBoxGenero.setSelectedIndex(-1);
                comboBoxIdade.setSelectedIndex(-1);
                salasComboBox.setSelectedIndex(-1);
                datePicker.getModel().setValue(null); // limpa a data

                limparFiltrosLabel.setVisible(false);

                // Voltar a mostrar todos os filmes com sessão
                Set<String> nomes = new HashSet<>();
                List<Filme> filmesTodos = bd.getFilmes().stream()
                        .filter(f -> f.isComSessao() && nomes.add(f.getNome()))
                        .collect(Collectors.toList());

                desenharCartazes(filmesTodos);
            }
        });
        //----------------------- LABEL LIMPAR FILTRO -------------------------------------------

        //------------------------------- BOTAO FILTRAR ---------------------------------------
        botaoFiltrar = new JButton();
        botaoFiltrar = new RoundedButton("Filtrar", 20);
        botaoFiltrar.setFont(new Font("Georgia", Font.PLAIN, 25));
        botaoFiltrar.setBackground(corFundoLabel);
        botaoFiltrar.setForeground(corFontePreto);
        botaoFiltrar.setFocusPainted(false);

        botaoFiltrar.addActionListener(e -> {
            limparFiltrosLabel.setVisible(true);

            String sessaoSel = (String) comboBoxSessoes.getSelectedItem();
            if (comboBoxSessoes.getSelectedIndex() < 0) sessaoSel = null;
            String generoSel = (String) comboBoxGenero.getSelectedItem();
            if (comboBoxGenero.getSelectedIndex() < 0) generoSel = null;
            String idadeSel = (String) comboBoxIdade.getSelectedItem();
            if (comboBoxIdade.getSelectedIndex() < 0) idadeSel = null;
            String salaSel = (String) salasComboBox.getSelectedItem();
            if (salasComboBox.getSelectedIndex() < 0) salaSel = null;

            Date dataSel = null;
            if (datePicker.getModel().getValue() != null) {
                dataSel = (Date) datePicker.getModel().getValue();
            }

            List<Filme> filmesFiltrados = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            for (Sessao sessao : bd.getSessoes()) {
                Filme f = sessao.getFilme();
                boolean ok = true;

                if (sessaoSel != null && !sessaoSel.isEmpty()) {
                    String hora = String.format("%02d:%02d", sessao.getHora(), sessao.getMinuto());
                    if (!hora.equals(sessaoSel)) ok = false;
                }

                if (generoSel != null && !generoSel.equals("Género")) {
                    Genero generoSelecionado = Genero.valueOf(NormalizarEnum.normalizarEnum(generoSel));
                    if (!f.getGeneros().contains(generoSelecionado)) {
                        ok = false;
                    }
                }


                if (idadeSel != null && !idadeSel.isEmpty()) {
                    if (!f.getIdade().equalsIgnoreCase(idadeSel)) ok = false;
                }

                if (salaSel != null && !salaSel.equals("Sala")) { // placeholder
                    boolean temSessaoComTipoSala = false;

                    for (Sessao s : bd.getSessoes()) {
                        if (s.getFilme().getNome().equals(f.getNome())) {
                            if (s.getSala().getTipo().equalsIgnoreCase(salaSel)) {
                                temSessaoComTipoSala = true;
                                break;
                            }
                        }
                    }

                    if (!temSessaoComTipoSala) ok = false;
                }

                if (dataSel != null) {
                    String sessaoData = String.format("%02d-%02d-%04d", sessao.getDia(), sessao.getMes(), sessao.getAno());
                    if (!sessaoData.equals(sdf.format(dataSel))) ok = false;
                }

                if (ok && !filmesFiltrados.contains(f)) filmesFiltrados.add(f);
            }

            desenharCartazes(filmesFiltrados);
        });
        //------------------------------- BOTAO FILTRAR ---------------------------------------




        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(bilheteiraLabel, "x 510, y 30");
        mainPanel.add(comboBoxSessoes, "x 80, y 255, w 195, h 50");
        mainPanel.add(datePicker, "x 290, y 257, w " + largura + ", h " + altura);
        mainPanel.add(comboBoxGenero, "x 507, y 257, w 195, h 50");
        mainPanel.add(comboBoxIdade, "x 757, y 257, w 175, h 50");
        mainPanel.add(salasComboBox, "x 945, y 257, w 180, h 50");
        mainPanel.add(scrollPaneCartazes, "x 50, y 335, w 1190, h 390");
        mainPanel.add(botaoFiltrar, "x 1150, y 257, w 100, h 48");
        mainPanel.add(limparFiltrosLabel, "x 1100, y 210, w 200, h 30");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaInicial();
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

    public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parse(text);
        }

        @Override
        public String valueToString(Object value) {
            if (value == null) {
                return "";
            }

            if (value instanceof java.util.Calendar) {
                return dateFormatter.format(((java.util.Calendar) value).getTime());
            }

            if (value instanceof java.util.Date) {
                return dateFormatter.format((java.util.Date) value);
            }

            return value.toString(); // fallback
        }
    }


    private void desenharCartazes(List<Filme> filmes) {
        cartazPanel.removeAll();

        Set<String> nomesAdicionados = new HashSet<>();

        for (Filme filme : filmes) {
            // Evita cartazes repetidos para filmes com o mesmo nome
            if (!nomesAdicionados.contains(filme.getNome())) {
                nomesAdicionados.add(filme.getNome());

                JLabel cartaz = new JLabel();
                ImageIcon cartazIcon = new ImageIcon(getClass().getResource("/imagens/cartazes/" + filme.getFoto()));
                Image cartazImg = cartazIcon.getImage().getScaledInstance(265, 390, Image.SCALE_SMOOTH);
                cartaz.setIcon(new ImageIcon(cartazImg));

                // Adiciona cursor de "mão" para indicar clique
                cartaz.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                // Adiciona evento de clique
                cartaz.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        app.mostrarEscolhaFilmeBilhteira(filme);
                    }
                });


                cartazPanel.add(cartaz);
            }
        }

        cartazPanel.revalidate();
        cartazPanel.repaint();
    }



    public JPanel getMainPanel() {
        return mainPanel;
    }
}
