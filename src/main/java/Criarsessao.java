import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.*;

public class Criarsessao {
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel sessaoLabel = new JLabel("Sessões - Criação");
    private final AppWindow app;

    private JComboBox<String> comboFilmes;
    private JComboBox<String> comboSala;
    private JComboBox<String> comboEstado1;
    private JDatePickerImpl datePicker;
    private JSpinner horaSpinner;
    private JButton botaoCriar;

    // Cores
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");

    public Criarsessao(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {
        Font fonteTitulo = new Font("Georgia", Font.PLAIN, 100);
        Font fonteSubtitulo = new Font("Georgia", Font.PLAIN, 100);
        Font fonteCampos = new Font("Georgia", Font.PLAIN, 25);
        mainPanel.setLayout(new MigLayout("insets 20, fillx, wrap 3", "[grow][grow][grow]", "[][][][][][]"));
        mainPanel.setBackground(corFundo);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(175, 175, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));


        // seta andar para atras
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

// Ação ao clicar na seta: voltar para a página principal do admin
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarAdmin(); // Altere este método conforme o nome correto no seu AppWindow
            }
        });


        // Labels
        adminLabel.setForeground(corFundoLabel);
        adminLabel.setFont(fonteTitulo);
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminLabel.setOpaque(false);

        sessaoLabel.setForeground(corFundoLabel);
        sessaoLabel.setFont(fonteSubtitulo);
        sessaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sessaoLabel.setOpaque(false);

        // ComboBoxes
        comboFilmes = criarComboBox(new String[]{
                "Paddington na Amazônia", "Aquaman 2 - O reino perdido", "Um filme Minecraft",
                "A semente do mal", "Vaiana 2"
        }, fonteCampos);

        comboSala = criarComboBox(new String[]{
                "Sala", "Sala 1", "Sala 2", "Sala 3", "Sala 4", "Sala 5"
        }, fonteCampos);

        comboEstado1 = criarComboBox(new String[]{"Estado", "Ativo", "Inativo"}, fonteCampos);

        // Date Picker
        UtilDateModel dateModel = new UtilDateModel();
        dateModel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Hoje");
        p.put("text.month", "Mês");
        p.put("text.year", "Ano");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.getJFormattedTextField().setBackground(corFundoComponentes);
        datePicker.getJFormattedTextField().setFont(fonteCampos);
        ((JButton) datePicker.getComponent(1)).setBackground(corFundoComponentes);
        ((JButton) datePicker.getComponent(1)).setText("...");

        // Hora
        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.MINUTE);
        horaSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(horaSpinner, "HH:mm");
        horaSpinner.setEditor(editor);
        JComponent editorComp = horaSpinner.getEditor();
        if (editorComp instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editorComp).getTextField();
            textField.setBackground(corFundoComponentes);
            textField.setFont(fonteCampos);
        }

        // Botão Criar
        botaoCriar = new RoundedButton("Criar", 20);
        botaoCriar.setBackground(Color.decode("#FFA31A"));
        botaoCriar.setFont(fonteCampos);
        botaoCriar.setForeground(Color.BLACK);
        botaoCriar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Sessão criada."));

        // Layout
        mainPanel.add(new JLabel(""), "span 3, height 60!, wrap");

        mainPanel.add(logoLabel, "cell 0 0, align left");
        mainPanel.add(adminLabel, "cell 1 0 1 1, align center, wrap");
        mainPanel.add(voltaLabel, "cell 0 1, align left, gaptop -20");
        mainPanel.add(sessaoLabel, "cell 1 1 1 1, align center, wrap");

        mainPanel.add(new JLabel(""), "span 1, height 60!, wrap");

        mainPanel.add(comboFilmes, "width 180!, gapleft 40");
        mainPanel.add(comboSala, "width 180!, align center");
        mainPanel.add(comboEstado1, "width 180!, gapright 40, wrap");

        mainPanel.add(new JLabel(""), "span 1, height 60!, wrap");

        mainPanel.add(datePicker, "width 180!, gapleft 40");
        mainPanel.add(horaSpinner, "width 120!, align center");
        mainPanel.add(botaoCriar, "width 180!, gapright 40, wrap");
    }

    private JComboBox<String> criarComboBox(String[] itens, Font fonte) {
        JComboBox<String> combo = new JComboBox<>(itens);
        combo.setBackground(corFundoComponentes);
        combo.setFont(fonte);
        combo.setUI(new RoundedComboBoxUI());
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setHorizontalAlignment(CENTER);
                return c;
            }
        });
        return combo;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parse(text);
        }

        @Override
        public String valueToString(Object value) {
            if (value == null) return "";
            if (value instanceof Calendar) {
                return dateFormatter.format(((Calendar) value).getTime());
            }
            if (value instanceof java.util.Date) {
                return dateFormatter.format((java.util.Date) value);
            }
            return value.toString();
        }
    }
}

class RoundedComboBoxUI extends BasicComboBoxUI {
    @Override
    protected JButton createArrowButton() {
        JButton button = new JButton("\u25BC");
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBackground(Color.decode("#FFC133"));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        g.setColor(comboBox.getBackground());
        g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 20, 20);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        hasFocus = false;
        super.paint(g, c);
    }
}
