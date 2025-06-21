import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Carrinho {
    private JPanel mainPanel;
    private JLabel carrinhoLabel;
    private JLabel voltaLabel;
    private JLabel logoLabel;
    private JLabel infoSemCompras;
    private JLabel precoTotal;
    private final AppWindow app;
    private BaseDados bd;
    private JComboBox comboBoxDesconto;
    private JScrollPane scrollPaneCarrinho;
    private JPanel carrinhoPanel;

    private JButton aplicarButton;
    private JButton pagamentoButton;

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
    public Carrinho(AppWindow app) {
        this.app = app;
        this.bd = BaseDados.getInstance();
        configurarComponentes();
    }

    private void configurarComponentes(){
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

        infoSemCompras.setHorizontalAlignment(SwingConstants.CENTER);
        infoSemCompras.setForeground(corFontePreto);
        infoSemCompras.setBackground(corFundo);
        infoSemCompras.setFont(new Font("Georgia", Font.PLAIN, 50));
        infoSemCompras.setOpaque(true);
        infoSemCompras.setVisible(false);


        // -------------------- ComboBox Desconto --------------------------
        String[] opcoes = {"Infantil", "Estudante", "Senior", "Veterano"};
        comboBoxDesconto = new RoundedComboBox<>(opcoes, 20);
        comboBoxDesconto.setCursor(new Cursor(Cursor.HAND_CURSOR));

        comboBoxDesconto.setUI(new BasicComboBoxUI() {
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
        comboBoxDesconto.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Desconto");
                    label.setForeground(corFonte);
                    comboBoxDesconto.setFont(new Font("Georgia", Font.PLAIN, 35));
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

        comboBoxDesconto.setFont(new Font("Georgia", Font.PLAIN, 25));
        comboBoxDesconto.setForeground(corFontePreto);
        comboBoxDesconto.setBackground(corFundoComponentes);

        comboBoxDesconto.setEditable(false);
        // -------------------- ComboBox desconto --------------------------


        //---------------------------- BOTAO aplicar desconto ------------------------------
        aplicarButton = new RoundedButton("Aplicar", 20);
        aplicarButton.setFont(new Font("Georgia", Font.PLAIN, 30));
        aplicarButton.setBackground(corFundoLabel);
        aplicarButton.setForeground(corFontePreto); // texto
        aplicarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        for (ObjetoCarrinho objetoCarrinho : bd.getElementosCarrinho()){
            System.out.println(objetoCarrinho.getDesconto());
            if(objetoCarrinho.getDesconto() == 0.5){
                //infantil
                comboBoxDesconto.setSelectedIndex(0);
            }else if(objetoCarrinho.getDesconto() == 0.3){
                //estudante
                comboBoxDesconto.setSelectedIndex(1);
            } else if(objetoCarrinho.getDesconto() == 0.2){
                //senior
                comboBoxDesconto.setSelectedIndex(2);
            }else if(objetoCarrinho.getDesconto() == 0.1){
                //veterano
                comboBoxDesconto.setSelectedIndex(3);
            }else {
                // Não selecionar nenhum item no início → mostra placeholder
                comboBoxDesconto.setSelectedItem(null);
            }

            break;
        }

        aplicarButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(comboBoxDesconto.getSelectedItem() );

                        if(comboBoxDesconto.getSelectedItem() == null){
                            JOptionPane.showMessageDialog(null, "Desconto não selecionado!");
                        }else {
                            float desconto = 0;

                            if("Infantil".equals(comboBoxDesconto.getSelectedItem())){
                                desconto = 0.5F;
                            }else if("Estudante".equals(comboBoxDesconto.getSelectedItem())){
                                desconto = 0.3F;
                            }else if("Senior".equals(comboBoxDesconto.getSelectedItem())){
                                desconto = 0.2F;
                            }else if("Veterano".equals(comboBoxDesconto.getSelectedItem())){
                                desconto = 0.1F;
                            }

                            for(ObjetoCarrinho objetoCarrinho : bd.getElementosCarrinho()){
                                objetoCarrinho.setDesconto(desconto);
                            }

                            precoTotal.setText("Total: " + String.format("%.2f €", bd.getTotalCarrinho() ).replace(".", ","));
                            adicionarProdutos(bd.getElementosCarrinho());
                            mainPanel.revalidate();
                            mainPanel.repaint();
                        }
                    }
                }
        );
        //------------------------------- produtos  ----------------------------------

        // Painel para cartazes
        carrinhoPanel = new JPanel(new GridLayout(0, 3, 15, 15));
        carrinhoPanel.setBackground(corFundo);

        // ScrollPane
        scrollPaneCarrinho = new JScrollPane(carrinhoPanel);
        scrollPaneCarrinho.setBorder(null);
        scrollPaneCarrinho.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneCarrinho.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneCarrinho.getVerticalScrollBar().setUnitIncrement(12);

        mainPanel.add(scrollPaneCarrinho);
        this.adicionarProdutos(bd.getElementosCarrinho());


        // ----- price ----
        precoTotal.setHorizontalAlignment(SwingConstants.CENTER);
        precoTotal.setForeground(corFontePreto);
        precoTotal.setBackground(corFundo);
        precoTotal.setFont(new Font("Georgia", Font.PLAIN, 40));
        precoTotal.setOpaque(true);
        precoTotal.setText("Total: " + String.format("%.2f €", bd.getTotalCarrinho()).replace(".", ","));
        precoTotal.setVisible(true);

        //pagamento
        pagamentoButton = new RoundedButton("Pagamento", 20);
        pagamentoButton.setFont(new Font("Georgia", Font.PLAIN, 40));
        pagamentoButton.setBackground(corFundoLabel);
        pagamentoButton.setForeground(corFontePreto); // texto
        pagamentoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 30, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(carrinhoLabel, "x 500, y 20");
        mainPanel.add(infoSemCompras, "x 530, y 400");
        mainPanel.add(aplicarButton, "x 1000, y 205, w 70, h 50");
        mainPanel.add(comboBoxDesconto, "x 300, y 205, w 600, h 30");
        mainPanel.add(scrollPaneCarrinho, "x 50, y 290, w 1190, h 310");
        mainPanel.add(precoTotal, "x 150, y 650");
        mainPanel.add(pagamentoButton, "x 550, y 650, w 550, h 50");

        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Página Principal
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaInicial();
            }
        });

        pagamentoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarCarrinhoPagamento(bd.getTotalCarrinho());
            }
        });

        if(bd.getElementosCarrinho().size() == 0){
            infoSemCompras.setVisible(true);
            aplicarButton.setVisible(false);
            scrollPaneCarrinho.setVisible(false);
            comboBoxDesconto.setVisible(false);
            precoTotal.setVisible(false);
            pagamentoButton.setVisible(false);
        }

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void adicionarProdutos(List<ObjetoCarrinho> carrinhoList) {
        carrinhoPanel.removeAll();

        for (ObjetoCarrinho objecto : carrinhoList) {

            JLabel imagem = new JLabel();
            JLabel nome = new JLabel();
            JLabel preco = new JLabel();

            JPanel produtoPanel = new JPanel();
            produtoPanel.setLayout(new BoxLayout(produtoPanel, BoxLayout.Y_AXIS));
            produtoPanel.setBackground(corFundo);
            produtoPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

            if (objecto.getObjeto() instanceof Produto) {
                ImageIcon icon = new ImageIcon(((Produto) objecto.getObjeto()).getFoto());

                //-------- personalizar tamanho das fotos consoante o tipo de produto --------
                if(((Produto) objecto.getObjeto()).getTipoProduto() == TipoProduto.BEBIDA)
                {
                    Image produtoImg = icon.getImage().getScaledInstance(70, 190, Image.SCALE_DEFAULT);
                    imagem.setIcon(new ImageIcon(produtoImg));
                }
                if(((Produto) objecto.getObjeto()).getTipoProduto() == TipoProduto.APERITIVO)
                {
                    Image produtoImg = icon.getImage().getScaledInstance(150, 200, Image.SCALE_DEFAULT);
                    imagem.setIcon(new ImageIcon(produtoImg));
                }
                if(((Produto) objecto.getObjeto()).getTipoProduto() == TipoProduto.PACK)
                {
                    Image produtoImg = icon.getImage().getScaledInstance(150, 200, Image.SCALE_DEFAULT);
                    imagem.setIcon(new ImageIcon(produtoImg));
                }

                //-------- personalizar tamanho das fotos consoante o tipo de produto --------


                nome.setText(((Produto) objecto.getObjeto()).getNome());
                nome.setFont(new Font("Georgia", Font.PLAIN, 20));
                nome.setForeground(corFontePreto);
                Float precoVenda = ((Produto) objecto.getObjeto()).getPrecoVendaUnidade();

                preco.setText(String.format("%.2f €", precoVenda * (1 - objecto.getDesconto())).replace(".", ","));
                preco.setFont(new Font("Georgia", Font.PLAIN, 20));
                preco.setForeground(corFontePreto);

            } else if (objecto.getObjeto() instanceof Bilhete) {
                Bilhete bilhete = (Bilhete) objecto.getObjeto();
                ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/bilhete.png"));
                Image bilheteImg = icon.getImage().getScaledInstance(170, 200, Image.SCALE_SMOOTH);
                imagem.setIcon(new ImageIcon(bilheteImg));

                String nomeBilhete = bilhete.getSessao().getFilme().getNome() + " (" + bilhete.getLugar() + " )";
                nome.setText("Bilhete " + nomeBilhete);
                nome.setFont(new Font("Georgia", Font.PLAIN, 16));
                nome.setForeground(corFontePreto);

                Float precoVenda = bilhete.getSessao().getPrecoBilhete();
                preco.setText(String.format("%.2f €", precoVenda * (1 - objecto.getDesconto())).replace(".", ","));
                preco.setFont(new Font("Georgia", Font.PLAIN, 20));
                preco.setForeground(corFontePreto);

            }

            produtoPanel.add(imagem);
            produtoPanel.add(Box.createVerticalStrut(15));
            produtoPanel.add(nome);
            produtoPanel.add(Box.createVerticalStrut(10));
            produtoPanel.add(preco);

            if (objecto.getObjeto() instanceof Produto) {
                JSpinner spinner = new JSpinner(new SpinnerNumberModel(
                        objecto.getQuantidade(),
                        0,
                        ((Produto) objecto.getObjeto()).getStock(),
                        1));

                JComponent editor = spinner.getEditor();
                JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
                tf.setColumns(2);

                spinner.setMaximumSize(new Dimension(70, 30));
                spinner.setAlignmentX(Component.CENTER_ALIGNMENT);

                produtoPanel.add(Box.createVerticalStrut(10));
                produtoPanel.add(spinner);

                imagem.setAlignmentX(Component.CENTER_ALIGNMENT);
                nome.setAlignmentX(Component.CENTER_ALIGNMENT);
                preco.setAlignmentX(Component.CENTER_ALIGNMENT);
                spinner.setAlignmentX(Component.CENTER_ALIGNMENT);

                spinner.addChangeListener(e -> {
                    int quantidade = (int) spinner.getValue();

                    objecto.setQuantidade(quantidade);
                    bd.adicionarAoCarrinho(objecto);
                    bd.gravarDados();

                    if (bd.getElementosCarrinho().size() == 0) {
                        infoSemCompras.setVisible(true);
                        aplicarButton.setVisible(false);
                        scrollPaneCarrinho.setVisible(false);
                        comboBoxDesconto.setVisible(false);
                        precoTotal.setVisible(false);
                        pagamentoButton.setVisible(false);
                    }

                    adicionarProdutos(bd.getElementosCarrinho());
                    precoTotal.setText("Total: " + String.format("%.2f €", bd.getTotalCarrinho()).replace(".", ","));

                    mainPanel.revalidate();
                    mainPanel.repaint();
                });
            } else {
                imagem.setAlignmentX(Component.CENTER_ALIGNMENT);
                nome.setAlignmentX(Component.CENTER_ALIGNMENT);
                preco.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Botão de remover bilhete
                JButton removerBtn = new RoundedButton("Remover", 20);
                removerBtn.setBackground(corFundoLabel);
                removerBtn.setForeground(corFontePreto);
                removerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                removerBtn.setFont(new Font("Georgia", Font.PLAIN, 16));
                removerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                removerBtn.addActionListener(e -> {
                    bd.removerDoCarrinho(objecto);
                    bd.gravarDados();
                    adicionarProdutos(bd.getElementosCarrinho());
                    precoTotal.setText("Total: " + String.format("%.2f €", bd.getTotalCarrinho()).replace(".", ","));

                    mainPanel.revalidate();
                    mainPanel.repaint();
                });

                produtoPanel.add(Box.createVerticalStrut(10));
                produtoPanel.add(removerBtn,"w 30, h 20");
            }

            carrinhoPanel.add(produtoPanel);
        }

        carrinhoPanel.revalidate();
        carrinhoPanel.repaint();
    }

}
