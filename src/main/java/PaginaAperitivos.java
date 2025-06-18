import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PaginaAperitivos {
    private JPanel mainPanel;
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel tituloPrincipalLabel = new JLabel("Bar");
    private JLabel subtituloLabel = new JLabel("Aperitivos");
    private AppWindow app;

    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private final Color corFontePreto = Color.decode("#000000");
    private final Color corBotaoLaranjaEscura = Color.decode("#FBA720");

    private BaseDados bd;

    public PaginaAperitivos(AppWindow app) {
        this.app = app;
        this.bd = BaseDados.getInstance();

        // Painel principal com BorderLayout
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(corFundo);

        // Painel de conteúdo com layout livre e altura estendida
        JPanel contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1200, 1000));
        contentPanel.setBackground(corFundo);

        // Scroll
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));
        logoLabel.setBounds(20, 10, 200, 200);
        contentPanel.add(logoLabel);

        // Voltar
        ImageIcon setaIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image setaImg = setaIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(setaImg));
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.setBounds(50, 220, 100, 100);
        voltaLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                app.mostrarBar();
            }
        });
        contentPanel.add(voltaLabel);

        // Título principal
        tituloPrincipalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloPrincipalLabel.setForeground(corFundoLabel);
        tituloPrincipalLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        tituloPrincipalLabel.setBounds(400, 30, 500, 100);
        tituloPrincipalLabel.setOpaque(true);
        tituloPrincipalLabel.setBackground(corFundo);
        contentPanel.add(tituloPrincipalLabel);

        // Subtítulo
        subtituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtituloLabel.setForeground(corFundoLabel);
        subtituloLabel.setFont(new Font("Georgia", Font.PLAIN, 75));
        subtituloLabel.setBounds(400, 200, 500, 80);
        contentPanel.add(subtituloLabel);

        // Carrinho
        JLabel carrinhoLabel = new JLabel();
        ImageIcon carrinhoIcon = new ImageIcon(getClass().getResource("/imagens/carrinho_sem_compras.png"));
        Image carrinhoImg = carrinhoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        carrinhoLabel.setIcon(new ImageIcon(carrinhoImg));
        carrinhoLabel.setBounds(1100, 220, 100, 100);
        carrinhoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        carrinhoLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                app.mostrarCarrinho();
            }
        });
        contentPanel.add(carrinhoLabel);

        // Lista dinâmica de aperitivos
        List<Produto> produtos = bd.getProdutosPorTipo(TipoProduto.APERITIVO);
        int colunas = 4;
        int espacamentoX = 270;
        int espacamentoY = 350;
        int baseX = 200;
        int baseY = 330;

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            int col = i % colunas;
            int row = i / colunas;
            int x = baseX + col * espacamentoX;
            int y = baseY + row * espacamentoY;

            adicionarAperitivo(contentPanel, produto.getNome(), produto.getFoto(), x, y, produto.getIdProduto());
        }
    }

    private void adicionarAperitivo(JPanel panel, String nome, String imagemPath, int x, int y, int idProduto) {
        JLabel imagemLabel = new JLabel();
        ImageIcon imgIcon = new ImageIcon(imagemPath);
        Image img = imgIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        imagemLabel.setIcon(new ImageIcon(img));
        imagemLabel.setBounds(x - 20, y, 140, 140);
        panel.add(imagemLabel);

        JLabel nomeLabel = new JLabel(nome, JLabel.CENTER);
        nomeLabel.setFont(new Font("Georgia", Font.PLAIN, 25));
        nomeLabel.setBounds(x - 50, y + 160, 200, 30);
        nomeLabel.setForeground(corFontePreto);
        panel.add(nomeLabel);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        spinner.setBounds(x, y + 200, 80, 35);
        panel.add(spinner);

        RoundedButton addButton = new RoundedButton("Adicionar", 20);
        addButton.setBounds(x - 20, y + 250, 150, 40);
        addButton.setBackground(corBotaoLaranjaEscura);
        addButton.setForeground(Color.BLACK);
        addButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        addButton.addActionListener(e -> {
            int quantidade = (int) spinner.getValue();

            Produto produto = bd.getProdutobyID(idProduto);
            ObjetoCarrinho objetoCarrinho = new ObjetoCarrinho(produto, quantidade);
            bd.adicionarAoCarrinho(objetoCarrinho);
            bd.gravarDados();

            JOptionPane.showMessageDialog(null, nome + " adicionado ao carrinho!");
        });
        panel.add(addButton);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
