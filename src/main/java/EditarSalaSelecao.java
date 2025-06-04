import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class EditarSalaSelecao {

    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel salasLabel = new JLabel("Salas - Editar");
    private JComboBox <Object> salasComboBox = new JComboBox<>();
    private JButton editarButton = new JButton("Editar");
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

    // Construtor
    public EditarSalaSelecao(AppWindow app) {
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

        // Seta andar para trás
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));

        // Admin label
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminLabel.setForeground(corFundoLabel);
        adminLabel.setBackground(corFundo);
        adminLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        adminLabel.setOpaque(true);

        // Salas label
        salasLabel.setHorizontalAlignment(SwingConstants.CENTER);
        salasLabel.setForeground(corFundoLabel);
        salasLabel.setBackground(corFundo);
        salasLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        salasLabel.setOpaque(true);

        // ComboBox de Salas

        // Dados das salas
        List<Sala> salas = bd.getSalas();
        List<Sessao> sessoes = bd.getSessoes();
        List<Sala> salasSemBilhetes = new ArrayList<>();

        sessoes.get(0).venderBilhete();     // Simula a venda de um bilhete para testar a lógica
        sessoes.get(0).getBilhetesVendidos();

        // Filtrar salas que não têm bilhetes associados
        for (Sala sala : salas) {
            boolean temBilhetes = false;
            for (Sessao sessao : sessoes) {
                if (sessao.getSala().equals(sala) && sessao.getBilhetesVendidos() > 0) {
                    temBilhetes = true;
                    break;
                }
            }
            if (!temBilhetes) {
                salasSemBilhetes.add(sala);
            }
        }
        // Criar array de strings com as designações das salas filtradas
        String[] opcoesSalas = salasSemBilhetes.stream()
                .map(Sala::getDesignacao)
                .toArray(String[]::new);

        salasComboBox = new RoundedComboBox<>(opcoesSalas, 20);
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
                    label.setText("Sala a editar");
                    label.setForeground(corFonte);
                    salasComboBox.setFont(new Font("Georgia", Font.PLAIN, 40));
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

        salasComboBox.setFont(new Font("Georgia", Font.PLAIN, 40));
        salasComboBox.setBackground(corFundoComponentes);
        salasComboBox.setEditable(false);
        salasComboBox.setFocusable(false);

        // Botão Editar
        editarButton = new RoundedButton("Editar", 20);
        editarButton.setFont(new Font("Georgia", Font.PLAIN, 40));
        editarButton.setBackground(corFundoLabel);
        editarButton.setForeground(corFontePreto); // texto

        // Reposicionamento dos componentes
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(salasLabel, "x 400, y 120");
        mainPanel.add(salasComboBox, "x 150, y 400, w 600, h 70");
        mainPanel.add(editarButton, "x 800, y 400, w 350, h 70");

        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Página Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarAdmin();
            }
        });

        // Redirecionar para EditarSala
        editarButton.addActionListener(e -> {
            Object selectedSala = salasComboBox.getSelectedItem();
            if (selectedSala != null) {
                app.mostrarEditarSala((String) selectedSala);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma sala para editar.", "Seleção de Sala", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
