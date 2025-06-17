import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import org.jdatepicker.impl.*;
import com.formdev.flatlaf.FlatLightLaf;

public class CriarSessão {
    private JPanel panelPrincipal;
    private JDatePickerImpl datePicker;
    private JSpinner horaSpinner;
    private JComboBox<String> comboFilmes;
    private JComboBox<String> comboSala;
    private JComboBox<String> comboEstado1;
    private JComboBox<String> comboEstado2;
    private JButton botaoCriar;

    public CriarSessão() {
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.decode("#FFE7A0")); // cor de fundo geral
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Font fonte = new Font("SansSerif", Font.BOLD, 14);

        // Filmes
        comboFilmes = new JComboBox<>(new String[]{"Paddington na Amazônia", "Aquaman 2 - O reino perdido", "Um filme Minecraft", "A semente do mal", "Vaiana 2"
        });
        comboFilmes.setBackground(Color.decode("#FFC133"));
        comboFilmes.setFont(fonte);

        // Salas
        comboSala = new JComboBox<>(new String[]{
                "Sala ","Sala 1", "Sala 2", "Sala 3", "Sala 4", "Sala 5"
        });
        comboSala.setBackground(Color.decode("#FFC133"));
        comboSala.setFont(fonte);

        // Estado (duplo combo box como na imagem)
        comboEstado1 = new JComboBox<>(new String[]{"Estado","Ativo", "Inativo"});
        comboEstado2 = new JComboBox<>(new String[]{"Estado","Ativo", "Inativo"});
        comboEstado1.setBackground(Color.decode("#FFC133"));
        comboEstado2.setBackground(Color.decode("#FFC133"));
        comboEstado1.setFont(fonte);
        comboEstado2.setFont(fonte);

        // Date picker
        UtilDateModel dateModel = new UtilDateModel();
        dateModel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Hoje");
        p.put("text.month", "Mês");
        p.put("text.year", "Ano");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.getJFormattedTextField().setBackground(Color.decode("#FFC133"));
        JButton dpButton = (JButton) datePicker.getComponent(1);
        dpButton.setBackground(Color.decode("#FFC133"));
        dpButton.setFont(fonte);
        datePicker.getJFormattedTextField().setFont(fonte);

        // Hora
        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.MINUTE);
        horaSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(horaSpinner, "HH:mm");
        horaSpinner.setEditor(editor);
        JComponent editorComp = horaSpinner.getEditor();
        if (editorComp instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editorComp).getTextField();
            textField.setBackground(Color.decode("#FFC133"));
            textField.setFont(fonte);
        }
        for (Component comp : horaSpinner.getComponents()) {
            if (comp instanceof JButton) {
                comp.setBackground(Color.decode("#FFC133"));
            }
        }

        // Botão Criar
        botaoCriar = new JButton("Criar");
        botaoCriar.setBackground(Color.decode("#FFA31A"));
        botaoCriar.setForeground(Color.BLACK);
        botaoCriar.setFont(fonte);
        botaoCriar.addActionListener(e -> {
            // Ação futura: criar sessão
            JOptionPane.showMessageDialog(null, "Sessão criada (simulado).");
        });

        // Layout
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; panelPrincipal.add(comboFilmes, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panelPrincipal.add(comboSala, gbc);
        gbc.gridx = 2; gbc.gridy = 0; panelPrincipal.add(comboEstado1, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panelPrincipal.add(datePicker, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panelPrincipal.add(horaSpinner, gbc);
        gbc.gridx = 2; gbc.gridy = 1; panelPrincipal.add(botaoCriar, gbc);
    }

    public JPanel getPanel() {
        return panelPrincipal;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Falha ao carregar FlatLaf.");
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sessões - Criação");
            frame.setContentPane(new CriarSessão().getPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
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
            if (value instanceof java.util.Calendar) {
                return dateFormatter.format(((Calendar) value).getTime());
            }
            if (value instanceof java.util.Date) {
                return dateFormatter.format((java.util.Date) value);
            }
            return value.toString();
        }
    }
}
