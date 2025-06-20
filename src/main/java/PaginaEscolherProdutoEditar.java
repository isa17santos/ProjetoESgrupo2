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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PaginaEscolherProdutoEditar {
    private JPanel mainPanel;
    private JLabel logoLabel;
    private JLabel voltaLabel;
    private JLabel adminLabel;
    private JLabel produtoLabel;
    private JComboBox <Object> comboBoxTipo = new JComboBox<>();
    private JComboBox <Object> produtosComboBox = new JComboBox<>();
    private JButton editarButton = new JButton("Editar");

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
    public PaginaEscolherProdutoEditar(AppWindow app) {
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
        voltaLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Admin label
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminLabel.setForeground(corFundoLabel);
        adminLabel.setBackground(corFundo);
        adminLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        adminLabel.setOpaque(true);

        // produto label
        produtoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        produtoLabel.setForeground(corFundoLabel);
        produtoLabel.setBackground(corFundo);
        produtoLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        produtoLabel.setOpaque(true);


        // -------------------- ComboBox produto --------------------------

        produtosComboBox = new RoundedComboBox<>(new String[0], 20);
        produtosComboBox.setSelectedItem(null);

        produtosComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(produtosComboBox) {
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
                return new JButton(new ArrowIcon(produtosComboBox)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        // Custom renderer com placeholder
        produtosComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Produto");
                    label.setForeground(corFonte);
                    produtosComboBox.setFont(new Font("Georgia", Font.PLAIN, 35));
                } else {
                    label.setForeground(corFontePreto);
                    produtosComboBox.setFont(new Font("Georgia", Font.PLAIN, 30));
                }

                if (index == -1) label.setBackground(corFundoComponentes);
                else if (isSelected) label.setBackground(corHoverComboBox);
                else label.setBackground(corFundoSubMenu);

                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                return label;
            }
        });

        produtosComboBox.setFont(new Font("Georgia", Font.PLAIN, 25));
        produtosComboBox.setForeground(corFontePreto);
        produtosComboBox.setBackground(corFundoComponentes);
        produtosComboBox.setEditable(false);
        produtosComboBox.setFocusable(false);
        produtosComboBox.setVisible(false);
        // -------------------- ComboBox produtos --------------------------


        // -------------------- ComboBox tipo --------------------------
        String[] opcoes = {"Bebida", "Aperitivo", "Pack"};
        comboBoxTipo = new RoundedComboBox<>(opcoes, 20);

        // Não selecionar nenhum item no início → mostra placeholder
        comboBoxTipo.setSelectedItem(null);

        comboBoxTipo.setUI(new BasicComboBoxUI() {
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
        comboBoxTipo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Tipo");
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

        comboBoxTipo.setFont(new Font("Georgia", Font.PLAIN, 25));
        comboBoxTipo.setForeground(corFontePreto);
        comboBoxTipo.setBackground(corFundoComponentes);
        comboBoxTipo.setEditable(false);


        comboBoxTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBoxTipo.getSelectedItem();
                produtosComboBox.removeAllItems(); // Clear previous items
                List<Produto> produtos = null;
                String[] nomes = null;

                System.out.println(selected);
                switch (selected) {
                    case "Bebida":
                        //obtém todos os produtos
                        produtos = BaseDados.getInstance().getProdutosPorTipo(TipoProduto.BEBIDA);

                        nomes = new String[produtos.size()];
                        for (int i = 0; i < produtos.size(); i++) {
                            nomes[i] = produtos.get(i).getNome();
                        }

                        System.out.println(Arrays.toString(nomes));
                        produtosComboBox.removeAllItems();
                        for (Produto p : produtos) {
                            produtosComboBox.addItem(p.getNome());
                        }

                        produtosComboBox.setVisible(true);
                        produtosComboBox.setSelectedItem(null);
                        editarButton.setVisible(true);
                        break;

                    case "Aperitivo":
                        //obtém todos os produtos
                        produtos = BaseDados.getInstance().getProdutosPorTipo(TipoProduto.APERITIVO);

                        nomes = new String[produtos.size()];
                        for (int i = 0; i < produtos.size(); i++) {
                            nomes[i] = produtos.get(i).getNome();
                        }

                        System.out.println(Arrays.toString(nomes));
                        produtosComboBox.removeAllItems();
                        for (Produto p : produtos) {
                            produtosComboBox.addItem(p.getNome());
                        }
                        produtosComboBox.setVisible(true);
                        produtosComboBox.setSelectedItem(null);
                        editarButton.setVisible(true);
                        break;
                    case "Pack":
                        produtos = BaseDados.getInstance().getProdutosPorTipo(TipoProduto.PACK);

                        nomes = new String[produtos.size()];
                        for (int i = 0; i < produtos.size(); i++) {
                            nomes[i] = produtos.get(i).getNome();
                        }

                        System.out.println(Arrays.toString(nomes));
                        produtosComboBox.removeAllItems();
                        for (Produto p : produtos) {
                            produtosComboBox.addItem(p.getNome());
                        }
                        produtosComboBox.setVisible(true);
                        produtosComboBox.setSelectedItem(null);
                        editarButton.setVisible(true);
                        break;
                }

                mainPanel.revalidate(); // Refresh layout
                mainPanel.repaint();
            }
        });




        // -------------------- botao editar --------------------------
        editarButton = new RoundedButton("Editar", 20);
        editarButton.setFont(new Font("Georgia", Font.PLAIN, 40));
        editarButton.setBackground(corFundoLabel);
        editarButton.setForeground(corFontePreto);
        editarButton.setVisible(false);



        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 500, y 40");
        mainPanel.add(produtoLabel, "x 300, y 150");
        mainPanel.add(comboBoxTipo, "x 350, y 300, w 600, h 70");
        mainPanel.add(produtosComboBox, "x 350, y 400, w 600, h 70");
        mainPanel.add(editarButton, "x 350, y 600, w 600, h 70");

        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Página Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaPrincipalProdutosBarAdmin();
            }
        });

        // Redirecionar para Editar
        editarButton.addActionListener(e -> {
            //vai buscar o produto pelo nome na base de dados
            String selectedProduto = produtosComboBox.getSelectedItem().toString();
            Produto produto = BaseDados.getInstance().getProdutosPorNome(selectedProduto.toString());

            app.mostrarPaginaEditarProduto(produto);

        });


    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
