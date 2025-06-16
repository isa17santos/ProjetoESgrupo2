import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CarrinhoPagamento {

    private JPanel mainPanel;
    private JLabel carrinhoLabel;
    private JLabel voltaLabel;
    private JLabel logoLabel;
    private JLabel pagamento;
    private JLabel totalPreco;
    private float total;
    private JButton pagarButton;


    private final AppWindow app;
    private BaseDados bd;

    private JComboBox metodoPagamento;

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
    public CarrinhoPagamento(AppWindow app, float total) {
        this.app = app;
        this.total = total;
        this.bd = BaseDados.getInstance();
        configurarComponentes();

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void configurarComponentes(){

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
        voltaLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // carrinho label
        carrinhoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        carrinhoLabel.setForeground(corFundoLabel);
        carrinhoLabel.setBackground(corFundo);
        carrinhoLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        carrinhoLabel.setOpaque(true);

        // carrinho pagamento label
        pagamento.setHorizontalAlignment(SwingConstants.CENTER);
        pagamento.setForeground(corFundoLabel);
        pagamento.setBackground(corFundo);
        pagamento.setFont(new Font("Georgia", Font.PLAIN, 100));
        pagamento.setOpaque(true);

        //pagamento
        String[] opcoes = {"Multibanco", "Dinheiro"};
        metodoPagamento = new RoundedComboBox<>(opcoes, 20);
        metodoPagamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        metodoPagamento.setSelectedItem(null);

        metodoPagamento.setUI(new BasicComboBoxUI() {
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
        metodoPagamento.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Método de pagamento");
                    label.setForeground(corFonte);
                    metodoPagamento.setFont(new Font("Georgia", Font.PLAIN, 35));
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

        metodoPagamento.setFont(new Font("Georgia", Font.PLAIN, 25));
        metodoPagamento.setForeground(corFontePreto);
        metodoPagamento.setBackground(corFundoComponentes);

        metodoPagamento.setEditable(false);


        metodoPagamento.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    //remove a label
                    mainPanel.remove(totalPreco);
                    //adicionar com novas posições
                    mainPanel.add(totalPreco, "x 360, y 600");
                    pagarButton.setVisible(true);
            }
        });

        //total
        totalPreco.setHorizontalAlignment(SwingConstants.CENTER);
        totalPreco.setForeground(corFontePreto);
        totalPreco.setBackground(corFundo);
        totalPreco.setFont(new Font("Georgia", Font.PLAIN, 40));
        totalPreco.setOpaque(true);
        totalPreco.setText("Total: " + String.format("%.2f €", this.total).replace(".", ","));
        totalPreco.setVisible(true);


        //botao pagar
        pagarButton = new RoundedButton("Pagar", 20);
        pagarButton.setFont(new Font("Georgia", Font.PLAIN, 40));
        pagarButton.setBackground(corFundoLabel);
        pagarButton.setForeground(corFontePreto);
        pagarButton.setVisible(false);
        pagarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        //redirecionamentos
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarCarrinho();
            }
        });

        pagarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                //retira o stock e o carrinho
                boolean stockBaixo = bd.pagamentoCarrinho();
                if(stockBaixo){
                    JOptionPane.showMessageDialog(null, "Alguns produtos estão com stock baixo.");
                }
                app.mostrarConfirmacaoCarrinhoPagamento();
            }
        });

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 30, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(carrinhoLabel, "x 500, y 20");
        mainPanel.add(pagamento, "x 450, y 120");
        mainPanel.add(metodoPagamento, "x 220, y 300, w 900, h 50");
        mainPanel.add(totalPreco, "x 560, y 600");
        mainPanel.add(pagarButton, "x 760, y 600, w 300, h 50");


    }
}
