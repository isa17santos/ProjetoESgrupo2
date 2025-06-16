import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class CriarSessao {
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel sessoesLabel = new JLabel("Sessões - Criação");
    private JComboBox<Object> comboBoxFilme = new JComboBox<>();
    private JComboBox<Object> comboBoxSala = new JComboBox<>();
    private JComboBox<Object> comboBoxEstado = new JComboBox<>();
    private JDatePickerImpl datePicker;
    private JSpinner timeSpinner;
    private JButton criarButton;

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

    public CriarSessao(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {
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

        // --------------------- SESSÕES LABEL -----------------------
        sessoesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sessoesLabel.setForeground(corFundoLabel);
        sessoesLabel.setBackground(corFundo);
        sessoesLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        sessoesLabel.setOpaque(true);
        // --------------------- SESSÕES LABEL -----------------------

        // --------------- COMBOBOX FILME --------------
        String[] opcoesFilme = {"Filme 1", "Filme 2", "Filme 3"}; // Substituir por lista real de opções
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
        String[] opcoesSala = {"Sala 1", "Sala 2", "Sala 3"}; // Substituir por lista real de opções
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

        // --------------- COMBOBOX ESTADO --------------
        String[] opcoesEstado = {"Ativo", "Inativo"}; // Substituir por lista real de opções
        comboBoxEstado = new RoundedComboBox<>(opcoesEstado, 20);
        comboBoxEstado.setSelectedItem(null);
        comboBoxEstado.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBoxEstado) {
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
                return new JButton(new ArrowIcon(comboBoxEstado)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });
        comboBoxEstado.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
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
        // --------------- COMBOBOX ESTADO --------------

        // --------------- DATE PICKER --------------
        UtilDateModel dateModel = new UtilDateModel();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 5); // Set to today: June 05, 2025
        dateModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dateModel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBackground(corFundoComponentes);
        datePicker.getJFormattedTextField().setFont(new Font("Georgia", Font.PLAIN, 35));
        datePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
        datePicker.getJFormattedTextField().setForeground(corFonte);
        // --------------- DATE PICKER --------------

        // --------------- TIME SPINNER --------------
        SpinnerDateModel timeModel = new SpinnerDateModel();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 12); // Set to current time: 04:12 PM WEST
        timeModel.setValue(calendar.getTime());
        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setBackground(corFundoComponentes);

        // Customize the spinner's UI to increase button width
        timeSpinner.setUI(new BasicSpinnerUI() {
            @Override
            protected Component createNextButton() {
                JButton button = (JButton) super.createNextButton();
                button.setPreferredSize(new Dimension(30, 25)); // Increase width to 30
                button.setBackground(corBotaoSetaComboBox); // Match combo box arrow button color
                return button;
            }

            @Override
            protected Component createPreviousButton() {
                JButton button = (JButton) super.createPreviousButton();
                button.setPreferredSize(new Dimension(30, 25)); // Increase width to 30
                button.setBackground(corBotaoSetaComboBox); // Match combo box arrow button color
                return button;
            }
        });

        JComponent editor = timeSpinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
            textField.setFont(new Font("Georgia", Font.PLAIN, 35));
            textField.setHorizontalAlignment(SwingConstants.CENTER);
            textField.setForeground(corFonte);
            textField.setBackground(corFundoComponentes); // Set the background to yellow
            textField.setOpaque(true); // Ensure the background color is visible
        }
        // --------------- TIME SPINNER --------------

        //----------------- BOTAO CRIAR -------------
        criarButton = new RoundedButton("Criar", 20);
        criarButton.setFont(new Font("Georgia", Font.BOLD, 30));
        criarButton.setBackground(corFundoLabel);
        criarButton.setForeground(corFontePreto);
        //----------------- BOTAO CRIAR -------------

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10"); mainPanel.add(voltaLabel, "x 30, y 200"); mainPanel.add(adminLabel, "x 500, y 40"); mainPanel.add(sessoesLabel, "x 350, y 180");
        mainPanel.add(comboBoxFilme, "x 100, y 375, w 250, h 40"); mainPanel.add(comboBoxSala, "x 560, y 375, w 250, h 40"); mainPanel.add(comboBoxEstado, "x 950, y 375, w 250, h 40");
        mainPanel.add(datePicker, "x 150, y 500, w 250, h 60"); mainPanel.add(timeSpinner, "x 560, y 500, w 250, h 60"); mainPanel.add(criarButton, "x 900, y 500, w 250, h 60");

        // ------------------- REDIRECIONAMENTOS -------------------
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaPrincipalSessoesAdmin();
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