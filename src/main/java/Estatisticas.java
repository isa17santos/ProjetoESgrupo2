import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class Estatisticas {
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel estatisticasLabel = new JLabel("Estatísticas");
    private JComboBox<Object> comboBoxEstatisticas = new JComboBox<>();
    private EstatisticasGraficoTabela graficoTabelaPanel = new EstatisticasGraficoTabela();

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
    public Estatisticas(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {

        // pagina principal
        mainPanel.setLayout(new MigLayout("nogrid, insets 0"));
        mainPanel.setBackground(corFundo);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logoo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(600, 280, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));
        logoLabel.setOpaque(true);
        logoLabel.setBackground(corFundo);


        // seta andar para atras
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));

        // --------------------- ADMIN LABEL -----------------------
        estatisticasLabel.setHorizontalAlignment(SwingConstants.CENTER);
        estatisticasLabel.setForeground(corFundoLabel);
        estatisticasLabel.setBackground(corFundo);
        estatisticasLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        estatisticasLabel.setOpaque(true);

        // -------------------- COMBOBOX Estatística --------------------------

        String[] opcoesEstatistica = {"Vendas por dia", "Vendas por mês", "Vendas por ano", "Vendas por sessão", "Vendas por filme", "Stock", "Produtos mais vendidos no bar", "Taxa de ocupação por sessão", "Taxa de ocupação por sala", "Lucros", "Géneros de filmes mais vistos", "Top 5 filmes mais vistos"};
        comboBoxEstatisticas = new RoundedComboBox<>(opcoesEstatistica, 20);

        // Não selecionar nenhum item no início → mostra placeholder
        comboBoxEstatisticas.setSelectedItem(null);

        comboBoxEstatisticas.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBoxEstatisticas) {
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
                return new JButton(new ArrowIcon(comboBoxEstatisticas)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        // Custom renderer com placeholder
        comboBoxEstatisticas.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Escolha a Estatística");
                    label.setForeground(corFonte);
                    comboBoxEstatisticas.setFont(new Font("Georgia", Font.PLAIN, 35));
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

        comboBoxEstatisticas.setFont(new Font("Georgia", Font.PLAIN, 25));
        comboBoxEstatisticas.setBackground(corFundoComponentes);

        comboBoxEstatisticas.setEditable(false);
        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 310, y 350");
        mainPanel.add(voltaLabel, "x 30, y 30");
        mainPanel.add(estatisticasLabel, "x 450, y 40");
        mainPanel.add(comboBoxEstatisticas, "x 140, y 200, w 1000, h 80");
        mainPanel.add(graficoTabelaPanel, "x 140, y 300, w 1000, h 400");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaInicial();
            }
        });

        // Redirecionar para estatísticas selecionadas

        comboBoxEstatisticas.addActionListener(e -> {
            // Clear the previous chart or table
            graficoTabelaPanel.clear();

            String selected = (String) comboBoxEstatisticas.getSelectedItem();
            if (selected == null) return;

            logoLabel.setVisible(false); // Hide the logo when a statistic is selected

            // Example: switch based on selected statistic
            switch (selected) {
                case "Vendas por dia":      // Gráfico de barras
                    Map<String, Integer> vendasPorDia = BaseDados.getInstance().vendasPorDia();
                    graficoTabelaPanel.showBarChart(vendasPorDia);
                    break;
                case "Vendas por mês":      // Gráfico de barras
                    Map<String, Integer> vendasPorMes = BaseDados.getInstance().vendasPorMes();
                    graficoTabelaPanel.showBarChart(vendasPorMes);
                    break;
                case "Vendas por ano":      // Gráfico de barras
                    Map<String, Integer> vendasPorAno = BaseDados.getInstance().vendasPorAno();
                    graficoTabelaPanel.showBarChart(vendasPorAno);
                    break;
                case "Vendas por sessão":       // Tabela
                    String [] columnsSessao = {"Filme", "Sessão", "Vendas"};
                    Object[][] dataSessao = BaseDados.getInstance().tabelaVendasPorSessao();
                    graficoTabelaPanel.showTable(columnsSessao, dataSessao);
                    break;
                case "Vendas por filme":        // Tabela
                    String[] columnsFilme = {"Filme", "Vendas"};
                    Object[][] dataFilme = BaseDados.getInstance().tabelaVendasPorFilme();
                    graficoTabelaPanel.showTable(columnsFilme, dataFilme);
                    break;
                case "Stock":             // Tabela
                    String[] columns = {"Produto", "Stock"};
                    Object[][] data = BaseDados.getInstance().tabelaStock();
                    graficoTabelaPanel.showTable(columns, data);
                    break;
                case "Produtos mais vendidos no bar":       // Gráfico de barras
                    Map<String, Integer> produtosMaisVendidos = BaseDados.getInstance().produtosMaisVendidos();
                    graficoTabelaPanel.showBarChart(produtosMaisVendidos);
                    break;
                case "Taxa de ocupação por sessão":     // Tabela
                    String[] columnsTaxaOcupacaoSessao = {"Sessão", "Taxa de Ocupação"};
                    Object[][] dataTaxaOcupacaoSessao = BaseDados.getInstance().tabelaTaxaOcupacaoPorSessao();
                    graficoTabelaPanel.showTable(columnsTaxaOcupacaoSessao, dataTaxaOcupacaoSessao);
                    break;
                case "Taxa de ocupação por sala":       // Gráfico de barras
                    Map<String, Double> taxaOcupacaoPorSala = BaseDados.getInstance().taxaOcupacaoPorSala();
                    graficoTabelaPanel.showBarChartDouble(taxaOcupacaoPorSala);
                    break;
                case "Lucros":      // Tabela
                    String[] columnsLucros = {"Sessão", "Lucro"};
                    Object[][] dataLucros = BaseDados.getInstance().tabelaLucros();
                    graficoTabelaPanel.showTable(columnsLucros, dataLucros);
                    break;
                case "Géneros de filmes mais vistos":       // Gráfico de barras
                    Map<String, Integer> generosMaisVistos = BaseDados.getInstance().generosMaisVistos();
                    graficoTabelaPanel.showBarChart(generosMaisVistos);
                    break;
                case "Top 5 filmes mais vistos":        // Gráfico de barras
                    Map<String, Integer> top5FilmesMaisVistos = BaseDados.getInstance().top5FilmesMaisVistos();
                    graficoTabelaPanel.showBarChart(top5FilmesMaisVistos);
                    break;
                default:
                    // Optionally clear or hide the panel
                    break;
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
