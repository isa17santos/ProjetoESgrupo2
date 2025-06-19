import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.List;

public class EscolherEditarSessaoAdmin {
    private JPanel mainPanel;
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel sessoesLabel = new JLabel("Sessões - Editar");
    private JLabel textoLabel = new JLabel("Qual a sessão que deseja editar?");
    private JComboBox <Object> filmesComboBox = new JComboBox<>();
    private JComboBox <Object> salasComboBox = new JComboBox<>();
    private JDatePickerImpl datePicker;
    private JSpinner horaSpinner;
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
    public EscolherEditarSessaoAdmin(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }


    private void configurarComponentes() {
        // todas as sessoes existentes
        List<Sessao> sessoes = bd.getSessoes();

        List<Sessao> sessoesSemBilhetesVendidos = new ArrayList<>();

        for (Sessao sessao : sessoes) {
            if(sessao.getBilhetesVendidos() == 0)
            {
                sessoesSemBilhetesVendidos.add(sessao);
            }
        }

        System.out.println("Sessoes Selecionados: " + sessoesSemBilhetesVendidos.size());

        // filmes existentes
        List<Filme> filmes = bd.getFilmes();

        List<String> filmesSelecionados = new ArrayList<>();

        for(Sessao sessao : sessoesSemBilhetesVendidos) {
            for(Filme filme : filmes) {
                if(sessao.getFilme() == filme && !filmesSelecionados.contains(filme.getNome() + " " + filme.getIdiomas() + " " + filme.getTipos())) {
                    filmesSelecionados.add(filme.getNome() + " " + filme.getIdiomas() + " " + filme.getTipos());
                }
            }
        }

        System.out.println("Filmes Selecionados: " + filmesSelecionados.size());

        //salas existentes
        List<Sala> salas = bd.getSalas();

        List<String> salasSelecionados = new ArrayList<>();

        for (Sessao sessao : sessoesSemBilhetesVendidos) {
            for(Sala sala : salas) {
                if(sessao.getSala() == sala && !salasSelecionados.contains(sala.getDesignacao() + " (" + sala.getTipo() + ")")) {
                    salasSelecionados.add(sala.getDesignacao() + " (" + sala.getTipo() + ")");
                }
            }
        }

        System.out.println("Salas Selecionados: " + salasSelecionados.size());

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


        //----------------- TEXTO LABEL -------------------
        textoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textoLabel.setForeground(corFontePreto);
        textoLabel.setBackground(corFundo);
        textoLabel.setFont(new Font("Georgia", Font.PLAIN, 40));
        textoLabel.setOpaque(true);
        //----------------- TEXTO LABEL -------------------


        // --------------------- COMBOBOX FILMES -------------------------
        // ComboBox de filmes
        filmesComboBox = new RoundedComboBox<>(filmesSelecionados.toArray(new String[0]), 20);
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
        salasComboBox = new RoundedComboBox<>(salasSelecionados.toArray(new String[0]), 20);
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
        horaSpinner = new JSpinner(new SpinnerDateModel());
        horaSpinner.setPreferredSize(new Dimension(130, 50));
        horaSpinner.setFont(new Font("Georgia", Font.PLAIN, 25));
        horaSpinner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        horaSpinner.setOpaque(true); // permite fundo personalizado

// Editor personalizado com formato HH:mm
        JSpinner.DateEditor editor = new JSpinner.DateEditor(horaSpinner, "HH:mm");
        horaSpinner.setEditor(editor);

// Aceder ao campo de texto interno do spinner e aplicar cor e fonte
        JComponent editorComp = horaSpinner.getEditor();
        if (editorComp instanceof JSpinner.DefaultEditor) {
            JTextField textFieldHora = ((JSpinner.DefaultEditor) editorComp).getTextField();
            textFieldHora.setBackground(Color.decode("#FFC133")); // fundo do campo
            textFieldHora.setFont(new Font("Georgia", Font.PLAIN, 25));
            textFieldHora.setForeground(Color.BLACK); // texto preto (opcional)
            textFieldHora.setCaretColor(Color.BLACK); // cursor preto
        }

        // ----------------------- HORA ------------------------


        //----------------- BOTAO EDITAR -------------
        editarButton = new RoundedButton("Editar", 20);
        editarButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        editarButton.setBackground(corFundoLabel);
        editarButton.setForeground(corFontePreto); // texto
        editarButton.addActionListener(e -> {
            Date dataSelecionada = (Date) datePicker.getModel().getValue();

            if (filmesComboBox.getSelectedItem() == null || filmesComboBox.getSelectedItem().toString().equals("Filme") ||
                    salasComboBox.getSelectedItem() == null || salasComboBox.getSelectedItem().toString().equals("Sala") ||
                    dataSelecionada == null) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obter filme selecionado
            String textoSelecionado = (String) filmesComboBox.getSelectedItem();

            List<Filme> filmesAtivos = new ArrayList<>();

            for(Sessao sessao : sessoesSemBilhetesVendidos){
                for (Filme f : filmes){
                    if (sessao.getFilme().getNome().equals(f.getNome())){
                        filmesAtivos.add(f);
                    }
                }
            }

            Filme filmeSelecionado = null;
            for (Filme f : filmesAtivos) {
                String opcao = f.getNome() + " " + f.getIdiomas() + " " + f.getTipos();
                if (opcao.equals(textoSelecionado)) {
                    filmeSelecionado = f;
                    break;
                }
            }

            // Obter sala selecionada
            String salaSelecionadaTexto = (String) salasComboBox.getSelectedItem();

            List<Sala> salasAtivas = new ArrayList<>();

            for(Sessao sessao : sessoesSemBilhetesVendidos){
                for (Sala s : salas){
                    if (sessao.getSala().getDesignacao().equals(s.getDesignacao())){
                        salasAtivas.add(s);
                    }
                }
            }

            Sala salaSelecionada = null;
            for (Sala s : salasAtivas) {
                String opcao = s.getDesignacao() + " (" + s.getTipo() + ")";
                if (opcao.equals(salaSelecionadaTexto)) {
                    salaSelecionada = s;
                    break;
                }
            }

            if (filmeSelecionado == null || salaSelecionada == null) {
                JOptionPane.showMessageDialog(null, "Filme ou sala inválida", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obter hora do spinner
            Date horaData = (Date) horaSpinner.getValue();
            Calendar calHora = Calendar.getInstance();
            calHora.setTime(horaData);
            int hora = calHora.get(Calendar.HOUR_OF_DAY);
            int minutos = calHora.get(Calendar.MINUTE);

            // Converter data
            Instant instant = dataSelecionada.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate data = zdt.toLocalDate();

            int dia = data.getDayOfMonth();
            int mes = data.getMonthValue();
            int ano = data.getYear();

            // Verificar se existe sessão correspondente
            Sessao sessaoParaEditar = null;
            for (Sessao s : sessoesSemBilhetesVendidos) {
                if (s.getFilme().equals(filmeSelecionado)
                        && s.getSala().equals(salaSelecionada)
                        && s.getAno() == ano
                        && s.getMes() == mes
                        && s.getDia() == dia
                        && s.getHora() == hora
                        && s.getMinuto() == minutos) {
                    sessaoParaEditar = s;
                    break;
                }
            }

            if (sessaoParaEditar != null) {
                // Redirecionar para a página de edição com a sessão encontrada
                app.mostrarEditarSessaoAdmin(sessaoParaEditar);
            } else {
                JOptionPane.showMessageDialog(null, "A sessão selecionada é inválida ou já tem bilhetes vendidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        //----------------- BOTAO EDITAR -------------


        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(sessoesLabel, "x 390, y 160");
        mainPanel.add(textoLabel, "x 390, y 290");
        mainPanel.add(filmesComboBox, "x 200, y 390, w 300, h 40");
        mainPanel.add(salasComboBox, "x 760, y 390, w 300, h 40");
        mainPanel.add(datePicker, "x 335, y 500, w " + largura + ", h " + altura);
        mainPanel.add(horaSpinner, "x 760, y 500, w 300, h 50");
        mainPanel.add(editarButton, "x 200, y 600, w 860, h 50");

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
