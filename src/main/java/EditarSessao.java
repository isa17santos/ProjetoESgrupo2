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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class EditarSessao {
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel sessoesLabel = new JLabel("Sessões - Editar");
    private JLabel perguntaLabel = new JLabel("Qual a sessão que deseja editar?");
    private JTextField numeroFilas = new JTextField(20);
    private RoundedTextField nomeSessao;
    private JTextField numeroLugaresFila = new JTextField(20);
    private JComboBox<Object> comboBoxFilme = new JComboBox<>();
    private JComboBox<Object> comboBoxSala = new JComboBox<>();
    private JDatePickerImpl datePicker;
    private JSpinner timeSpinner;
    private JButton editarButton = new JButton("Editar");
    private ArrowIcon arrowIcon = new ArrowIcon(null);

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
    private JPanel panel1;

    public EditarSessao(AppWindow app, String nomeSessao) {
        this.app = app;
        configurarComponentes(String.valueOf(this.nomeSessao));
    }

    // Buscar dados da sessão selecionada
    private void buscarDadosSessao(String nomeSessao) {
        // Implementação para buscar dados da sessão (a ser ajustada conforme necessário)
    }

    private void configurarComponentes(String nomeSessaoValue) {
        // Página principal
        mainPanel.setLayout(new MigLayout("nogrid, insets 0"));
        mainPanel.setBackground(corFundo);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));

        // Seta para voltar
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));

        // --------------------- ADMIN LABEL -----------------------
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminLabel.setForeground(corFundoLabel);
        adminLabel.setBackground(corFundo);
        adminLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        adminLabel.setOpaque(true);
        // --------------------- ADMIN LABEL -----------------------

        // --------------------- FILMES LABEL -----------------------
        sessoesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sessoesLabel.setForeground(corFundoLabel);
        sessoesLabel.setBackground(corFundo);
        sessoesLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        sessoesLabel.setOpaque(true);
        // --------------------- SESSÕES LABEL -----------------------

        // --------------------- PERGUNTA LABEL -----------------------
        perguntaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        perguntaLabel.setForeground(corFontePreto);
        perguntaLabel.setBackground(corFundo);
        perguntaLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        perguntaLabel.setOpaque(true);
        // --------------------- PERGUNTA LABEL -----------------------

        // --------------- CAIXA DE TEXTO NOME SESSÃO --------------
        nomeSessao = new RoundedTextField(1, 20);
        nomeSessao.setHorizontalAlignment(SwingConstants.CENTER);
        nomeSessao.setBackground(corFundoComponentes);
        nomeSessao.setFont(new Font("Georgia", Font.PLAIN, 35));
        nomeSessao.setText(nomeSessaoValue);
        nomeSessao.setForeground(corFonte);
        nomeSessao.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nomeSessao.getText().equals("Designação")) {
                    nomeSessao.setText("");
                    nomeSessao.setForeground(corFontePreto);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nomeSessao.getText().isEmpty()) {
                    nomeSessao.setText("Designação");
                    nomeSessao.setForeground(corFonte);
                }
            }
        });
        // --------------- CAIXA DE TEXTO NOME SESSÃO --------------

        // --------------- COMBOBOX FILME --------------
        String[] opcoesFilme = {"Filme 1", "Filme 2", "Filme 3"}; // Substituir por lista real de filmes
        comboBoxFilme = new RoundedComboBox<>(opcoesFilme, 20);
        comboBoxFilme.setSelectedItem(null);
        comboBoxFilme.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBoxFilme) {
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
                return new JButton(new ArrowIcon(comboBoxFilme)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });
        comboBoxFilme.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) {
                    label.setText("Filme");
                    label.setForeground(corFonte);
                    comboBoxFilme.setFont(new Font("Georgia", Font.PLAIN, 35));
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
        comboBoxFilme.setFont(new Font("Georgia", Font.PLAIN, 25));
        comboBoxFilme.setBackground(corFundoComponentes);
        comboBoxFilme.setEditable(false);
        // --------------- COMBOBOX FILME --------------

        // --------------- COMBOBOX SALA --------------
        String[] opcoesSala = {"Sala 1", "Sala 2", "Sala 3"}; // Substituir por lista real de salas
        comboBoxSala = new RoundedComboBox<>(opcoesSala, 20);
        comboBoxSala.setSelectedItem(null);
        comboBoxSala.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBoxSala) {
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
                return new JButton(new ArrowIcon(comboBoxSala)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });
        comboBoxSala.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) {
                    label.setText("Sala");
                    label.setForeground(corFonte);
                    comboBoxSala.setFont(new Font("Georgia", Font.PLAIN, 35));
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
        comboBoxSala.setFont(new Font("Georgia", Font.PLAIN, 25));
        comboBoxSala.setBackground(corFundoComponentes);
        comboBoxSala.setEditable(false);
        // --------------- COMBOBOX SALA --------------

        // --------------- DATE PICKER (DATA) --------------
        UtilDateModel model = new UtilDateModel();
        // Setar a data inicial como hoje (05-06-2025)
        Calendar initialDate = Calendar.getInstance();
        initialDate.set(2025, Calendar.JUNE, 5); // Updated to today's date
        model.setValue(initialDate.getTime());
        model.setSelected(true);

        Properties p = new Properties();
        p.put("text.today", "Hoje");
        p.put("text.month", "Mês");
        p.put("text.year", "Ano");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.getJFormattedTextField().setBackground(corFundoComponentes);
        datePicker.getJFormattedTextField().setForeground(corFonte);
        datePicker.getJFormattedTextField().setFont(new Font("Georgia", Font.PLAIN, 35));
        datePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
        datePicker.getJFormattedTextField().setBorder(BorderFactory.createEmptyBorder());
        // --------------- DATE PICKER (DATA) --------------

        // --------------- TIME SPINNER (HORA) --------------
        SpinnerDateModel timeModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setBackground(corFundoComponentes);
        timeSpinner.setForeground(corFonte);
        timeSpinner.setFont(new Font("Georgia", Font.PLAIN, 35));
        // Setar a hora inicial como 12:50 PM (current time on June 05, 2025)
        Calendar initialTime = Calendar.getInstance();
        initialTime.set(2025, Calendar.JUNE, 5, 12, 50); // Kept the time as 12:50 PM
        timeSpinner.setValue(initialTime.getTime());
        timeEditor.getTextField().setBackground(corFundoComponentes);
        timeEditor.getTextField().setForeground(corFonte);
        timeEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        timeEditor.getTextField().setBorder(BorderFactory.createEmptyBorder());
        // --------------- TIME SPINNER (HORA) --------------

        //----------------- BOTAO EDITAR -------------
        editarButton = new RoundedButton("Editar", 20);
        editarButton.setFont(new Font("Georgia", Font.BOLD, 30));
        editarButton.setBackground(corFundoLabel);
        editarButton.setForeground(corFontePreto);
        //----------------- BOTAO EDITAR -------------

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 500, y 40");
        mainPanel.add(sessoesLabel, "x 350, y 180");
        mainPanel.add(perguntaLabel, "x 350, y 300, w 700, h 50");
        mainPanel.add(comboBoxFilme, "x 370, y 400, w 300, h 50");
        mainPanel.add(comboBoxSala, "x 750, y 400, w 225, h 50");
        mainPanel.add(datePicker, "x 375, y 500, w 300, h 50");
        mainPanel.add(timeSpinner, "x 800, y 500, w 125, h 50");
        mainPanel.add(editarButton, "x 375, y 600, w 600, h 50");

        // ------------------- REDIRECIONAMENTOS -------------------
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaPrincipalSessoesAdmin();
            }
        });
        editarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String nome = nomeSessao.getText();
                String filmeSelecionado = (String) comboBoxFilme.getSelectedItem();
                String salaSelecionada = (String) comboBoxSala.getSelectedItem();
                Date dataSelecionada = (Date) datePicker.getModel().getValue();
                Date horaSelecionada = (Date) timeSpinner.getValue();

                // Validação básica
                if (nome.isEmpty() || filmeSelecionado == null || salaSelecionada == null || dataSelecionada == null || horaSelecionada == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Combina data e hora
                Calendar dataHora = Calendar.getInstance();
                dataHora.setTime(dataSelecionada);
                Calendar hora = Calendar.getInstance();
                hora.setTime(horaSelecionada);
                dataHora.set(Calendar.HOUR_OF_DAY, hora.get(Calendar.HOUR_OF_DAY));
                dataHora.set(Calendar.MINUTE, hora.get(Calendar.MINUTE));

                // Salvar os dados
                boolean sucesso = SessaoController.atualizarSessao(nome, filmeSelecionado, salaSelecionada, dataHora.getTime());

                if (sucesso) {
                        app.mostrarEditarSessaoAI(); // vai para a tela desejada
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar a sessão.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Formatter para o JDatePicker
    private static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private final String datePattern = "dd-MM-yyyy";
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws java.text.ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws java.text.ParseException {
            if (value == null || !(value instanceof Date)) {
                return "";
            }
            return dateFormatter.format((Date) value);
        }
    }
}