import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.List;

import com.formdev.flatlaf.FlatLightLaf;

public class CriarSessaoAdmin {
    private JPanel mainPanel;
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel sessoesLabel = new JLabel("Sessões - Criação");
    private JComboBox <Object> filmesComboBox = new JComboBox<>();
    private JComboBox <Object> salasComboBox = new JComboBox<>();
    private JComboBox comboBoxEstado;
    private JDatePickerImpl datePicker;
    private JSpinner horaSpinner;
    private JTextField precoCompraField;
    private JLabel erroPrecoCompraLabel = new JLabel();
    private final String placeholder = "Preço do bilhete(€)";
    private JButton criarButton;

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
    public CriarSessaoAdmin(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }


    private void configurarComponentes() {

        //todos os filmes existentes
        List<Filme> filmes = bd.getFilmes();

        System.out.println("Total filmes existentes: " + filmes.size());

        // filmes ativos
        List<Filme> filmesAtivos = new ArrayList<>();

        //apenas filmes que estejam ativos
        for (Filme filme : filmes) {
            if(filme.getEstado() == Estado.ATIVO) {
                filmesAtivos.add(filme);
            }
        }

        System.out.println("Total filmes ativos: " + filmesAtivos.size());

        //------ configura string filmes --------
        List<String> opcoesFilmes = new ArrayList<>();
        for(Filme filme : filmesAtivos) {
            opcoesFilmes.add(filme.getNome() + " " + filme.getIdiomas() + " " + filme.getTipos());
        }

        System.out.println("Total opcoes filmes: " + opcoesFilmes.size());


        //todas as salas
        List<Sala> salas = bd.getSalas();

        System.out.println("Total salas existentes: " + salas.size());

        // salas ativas
        List<Sala> salasAtivas = new ArrayList<>();

        // apenas salas que estejam ativas
        for (Sala sala : salas) {
            if(sala.getEstado() == Estado.ATIVO) {
                salasAtivas.add(sala);
            }
        }

        System.out.println("Total salas ativos: " + salasAtivas.size());

        //------ configura string salas
        List<String> opcoesSalas = new ArrayList<>();
        for(Sala sala : salasAtivas) {
            opcoesSalas.add(sala.getDesignacao() + " (" + sala.getTipo() + ")");
        }

        System.out.println("Total opcoes salas: " + opcoesSalas.size());

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



        // --------------------- COMBOBOX FILMES -------------------------
        // ComboBox de filmes
        filmesComboBox = new RoundedComboBox<>(opcoesFilmes.toArray(new String[0]), 20);
        // Não selecionar nenhum item no início → mostra placeholder
        filmesComboBox.setSelectedItem(null);
        filmesComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(filmesComboBox) {
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
                return new JButton(new ArrowIcon(filmesComboBox)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        // Custom renderer com placeholder
        filmesComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Filme");
                    label.setForeground(corFontePreto);
                    filmesComboBox.setFont(new Font("Georgia", Font.PLAIN, 25));
                } else {
                    label.setForeground(corFontePreto);
                    filmesComboBox.setFont(new Font("Georgia", Font.PLAIN, 25));
                }

                if (index == -1) label.setBackground(corFundoComponentes);
                else if (isSelected) label.setBackground(corHoverComboBox);
                else label.setBackground(corFundoSubMenu);

                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                return label;
            }
        });

        filmesComboBox.setFont(new Font("Georgia", Font.PLAIN, 25));
        filmesComboBox.setBackground(corFundoComponentes);
        filmesComboBox.setForeground(corFontePreto);
        filmesComboBox.setEditable(false);
        filmesComboBox.setFocusable(false);
        // --------------------- COMBOBOX FILMES -------------------------


        // --------------------- COMBOBOX SALAS -------------------------
        salasComboBox = new RoundedComboBox<>(opcoesSalas.toArray(new String[0]), 20);
        // Não selecionar nenhum item no início → mostra placeholder
        salasComboBox.setSelectedItem(null);
        salasComboBox.setMaximumRowCount(5);                    // Show scroll if more than 5
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
                    label.setForeground(corFonte);
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
        salasComboBox.setEditable(false);
        salasComboBox.setFocusable(false);
        // --------------------- COMBOBOX SALAS -------------------------


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


        // ----------------------- HORA ------------------------
        // Configurar modelo de hora
        SpinnerDateModel modelHora = new SpinnerDateModel();
        modelHora.setCalendarField(Calendar.MINUTE);
        horaSpinner = new JSpinner(modelHora);

        // Editor para mostrar no formato HH:mm
        JSpinner.DateEditor editor = new JSpinner.DateEditor(horaSpinner, "HH:mm");
        horaSpinner.setEditor(editor);

        // Mudar a cor de fundo do campo de texto
        JComponent editorComp = horaSpinner.getEditor();
        if (editorComp instanceof JSpinner.DefaultEditor) {
            JTextField textFieldHora = ((JSpinner.DefaultEditor) editorComp).getTextField();
            textFieldHora.setHorizontalAlignment(SwingConstants.CENTER);
            textFieldHora.setFont(new Font("Georgia", Font.PLAIN, 25));
            textFieldHora.setBackground(Color.decode("#FFC133"));
        }

        // Mudar cor dos botões (setas)
        for (Component comp : horaSpinner.getComponents()) {
            if (comp instanceof JButton) {
                comp.setBackground(Color.decode("#FAFAFA"));
            }
        }

        try {
            // Ativar FlatLaf
            UIManager.setLookAndFeel(new FlatLightLaf());

            // Customizar cores do Spinner com FlatLaf
            UIManager.put("Spinner.background", new Color(0xFFC133));
            UIManager.put("Spinner.editorBackground", new Color(0xFFC133));
            UIManager.put("Spinner.arrowButtonBackground", new Color(0xFFC133));
        } catch (Exception ex) {
            System.err.println("Falha ao carregar FlatLaf.");
        }
        // ----------------------- HORA ------------------------


        // --------------------- PRECO BILHETE ----------------
        precoCompraField = new RoundedTextField(1,20);
        precoCompraField.setText(placeholder);
        precoCompraField.setForeground(corFonte);
        precoCompraField.setHorizontalAlignment(SwingConstants.CENTER);
        precoCompraField.setBackground(corFundoComponentes);
        precoCompraField.setFont(new Font("Georgia", Font.PLAIN, 25));

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

        //----------------- BOTAO CRIAR -------------
        criarButton = new RoundedButton("Criar", 20);
        criarButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        criarButton.setBackground(corFundoLabel);
        criarButton.setForeground(corFontePreto); // texto
        criarButton.addActionListener(e -> {

            Date dataSelecionada = (Date) datePicker.getModel().getValue();
            String precoTexto = precoCompraField.getText().trim();

            // Validações iniciais
            if (filmesComboBox.getSelectedItem() == null || filmesComboBox.getSelectedItem().toString().equals("Filme") ||
                    salasComboBox.getSelectedItem() == null || salasComboBox.getSelectedItem().toString().equals("Sala") ||
                    comboBoxEstado.getSelectedItem() == null || comboBoxEstado.getSelectedItem().toString().equals("Estado") ||
                    dataSelecionada == null ||
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


            String textoSelecionado = (String) filmesComboBox.getSelectedItem();
            Filme filmeSelecionado = null;

            for (Filme f : filmesAtivos) {
                String opcao = f.getNome() + " " + f.getIdiomas() + " " + f.getTipos();
                if (opcao.equals(textoSelecionado)) {
                    filmeSelecionado = f;
                    break;
                }
            }

            if (filmeSelecionado == null) {
                JOptionPane.showMessageDialog(null, "Filme selecionado inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String salaSelecionadaTexto = (String) salasComboBox.getSelectedItem();
            Sala salaSelecionada = null;

            for (Sala s : salasAtivas) {
                String opcao = s.getDesignacao() + " (" + s.getTipo() + ")";
                if (opcao.equals(salaSelecionadaTexto)) {
                    salaSelecionada = s;
                    break;
                }
            }

            if (salaSelecionada == null) {
                JOptionPane.showMessageDialog(null, "Selecione uma sala válida.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }


            // Compatibilidade tipo filme <-> tipo sala
            String tipoFilme = filmeSelecionado.getTipos();
            String tipoSala = salaSelecionada.getTipo();
            boolean tipoCompativel = (tipoSala.equalsIgnoreCase("normal") && (tipoFilme.equalsIgnoreCase("2D") || tipoFilme.equalsIgnoreCase("3D")))
                    || (tipoSala.equalsIgnoreCase("5D") && tipoFilme.equalsIgnoreCase("5D"))
                    || (tipoSala.equalsIgnoreCase("vip") && tipoFilme.equalsIgnoreCase("2D"));

            if (!tipoCompativel) {
                JOptionPane.showMessageDialog(null, "Tipo de filme não compatível com tipo de sala.", "Erro", JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Obter hora e minutos do spinner corretamente
            Date horaData = (Date) horaSpinner.getValue();
            Calendar calHora = Calendar.getInstance();
            calHora.setTime(horaData);
            int hora = calHora.get(Calendar.HOUR_OF_DAY);
            int minutos = calHora.get(Calendar.MINUTE);

            LocalTime horaSelecionada = LocalTime.of(hora, minutos);
            if (horaSelecionada.isBefore(LocalTime.of(12, 0)) || horaSelecionada.isAfter(LocalTime.of(22, 30))) {
                JOptionPane.showMessageDialog(null, "A hora deve estar entre as 12:00 e as 22:30.", "Erro", JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Converter Date para LocalDate para facilitar a comparação
            Instant instant = dataSelecionada.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate data = zdt.toLocalDate();



            // Validar sobreposição de sessões
            if (existeSobreposicao(salaSelecionada, data, hora, minutos, filmeSelecionado)) {
                JOptionPane.showMessageDialog(null, "Já existe uma sessão nesta sala neste horário.", "Erro", JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Verificar se sessão exatamente igual já existe
            for (Sessao sessao : bd.getSessoes()) {
                if (sessao.getFilme().equals(filmeSelecionado)
                        && sessao.getSala().equals(salaSelecionada)
                        && sessao.getAno() == data.getYear()
                        && sessao.getMes() == data.getMonthValue()
                        && sessao.getDia() == data.getDayOfMonth()
                        && sessao.getHora() == hora
                        && sessao.getMinuto() == minutos) {

                    JOptionPane.showMessageDialog(null, "Já existe uma sessão com os mesmos dados.", "Erro", JOptionPane.ERROR_MESSAGE);

                    return;
                }
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

            int dia = data.getDayOfMonth();
            int mes = data.getMonthValue();
            int ano = data.getYear();

            // Criar e guardar a nova sessão
            Sessao novaSessao = new Sessao(filmeSelecionado, salaSelecionada, estadoSelecionado, dia, mes, ano, hora, minutos, preco);
            bd.getSessoes().add(novaSessao);
            filmeSelecionado.setComSessao(true);
            bd.gravarDados();

            app.mostrarConformacaoCriacaoSessaoAdmin();
        });

        //----------------- BOTAO CRIAR -------------

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(sessoesLabel, "x 450, y 110");
        mainPanel.add(filmesComboBox, "x 100, y 340, w 300, h 40");
        mainPanel.add(salasComboBox, "x 600, y 340, w 300, h 40");
        mainPanel.add(comboBoxEstado, "x 950, y 340, w 200, h 10");
        mainPanel.add(datePicker, "x 100, y 490, w " + largura + ", h " + altura);
        mainPanel.add(horaSpinner, "x 430, y 490, w 200, h 50");
        mainPanel.add(precoCompraField, "x 750, y 490, w 400, h 50");
        mainPanel.add(erroPrecoCompraLabel, "x 750, y 540, w 200, h 50");
        mainPanel.add(criarButton, "x 100, y 610, w 1050, h 50");

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

    // Método para verificar sobreposição
    private boolean existeSobreposicao(Sala sala, LocalDate data, int horaInicio, int minutoInicio, Filme filmeNovo) {
        LocalTime inicioNovaSessao = LocalTime.of(horaInicio, minutoInicio);
        LocalTime fimNovaSessao = inicioNovaSessao.plusMinutes(filmeNovo.getDuracao());

        for (Sessao sessao : bd.getSessoes()) {
            if (!sessao.getSala().equals(sala)) continue;

            if (sessao.getAno() == data.getYear() &&
                    sessao.getMes() == data.getMonthValue() &&
                    sessao.getDia() == data.getDayOfMonth()) {

                LocalTime inicioSessaoExistente = LocalTime.of(sessao.getHora(), sessao.getMinuto());
                LocalTime fimSessaoExistente = inicioSessaoExistente.plusMinutes(sessao.getFilme().getDuracao());

                // Verifica se os intervalos se sobrepõem
                boolean sobrepoe = !fimNovaSessao.isBefore(inicioSessaoExistente) && !inicioNovaSessao.isAfter(fimSessaoExistente);
                if (sobrepoe) return true;
            }
        }
        return false;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}





