import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginaPacks {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private AppWindow app;
    private Map<String, Integer> carrinho = new HashMap<>();

    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private final Color corFontePreto = Color.decode("#000000");
    private final Color corBotaoLaranjaEscura = Color.decode("#FBA720");

    private BaseDados bd;

    public PaginaPacks(AppWindow app) {
        this.app = app;
        this.bd = BaseDados.getInstance();
        configurarComponentes();
    }

    private void configurarComponentes() {
        contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1200, 1000));
        contentPanel.setBackground(corFundo);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(corFundo);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        configurarLogo();
        configurarSetaVoltar();
        configurarTitulos();
        configurarCarrinho();
        configurarListaDePacks();
    }

    private void configurarLogo() {
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));
        logoLabel.setBounds(20, 10, 200, 200);
        contentPanel.add(logoLabel);
    }

    private void configurarSetaVoltar() {
        JLabel voltaLabel = new JLabel();
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
    }

    private void configurarTitulos() {
        JLabel tituloPrincipalLabel = new JLabel("Bar", SwingConstants.CENTER);
        tituloPrincipalLabel.setForeground(corFundoLabel);
        tituloPrincipalLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        tituloPrincipalLabel.setOpaque(true);
        tituloPrincipalLabel.setBackground(corFundo);
        tituloPrincipalLabel.setBounds(400, 30, 500, 100);
        contentPanel.add(tituloPrincipalLabel);

        JLabel subtituloLabel = new JLabel("Packs", SwingConstants.CENTER);
        subtituloLabel.setForeground(corFundoLabel);
        subtituloLabel.setFont(new Font("Georgia", Font.PLAIN, 75));
        subtituloLabel.setBounds(400, 200, 500, 80);
        contentPanel.add(subtituloLabel);
    }

    private void configurarCarrinho() {
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
    }

    private void configurarListaDePacks() {
        List<Produto> produtos = bd.getProdutosPorTipo(TipoProduto.PACK);
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

            adicionarPack(contentPanel, produto.getNome(), produto.getFoto(), x, y, produto.getIdProduto());
        }
    }

    private void adicionarPack(JPanel panel, String nome, String imagemPath, int x, int y, int idProduto) {
        JLabel imagemLabel = new JLabel();
        ImageIcon imgIcon = new ImageIcon(imagemPath);
        Image img = imgIcon.getImage().getScaledInstance(200, 190, Image.SCALE_SMOOTH);
        imagemLabel.setIcon(new ImageIcon(img));
        imagemLabel.setBounds(x - 100, y, 200, 200);
        panel.add(imagemLabel);

        JLabel nomeLabel = new JLabel(nome, JLabel.CENTER);
        nomeLabel.setFont(new Font("Georgia", Font.PLAIN, 25));
        nomeLabel.setBounds(x - 90, y + 220, 200, 30);
        nomeLabel.setForeground(corFontePreto);
        panel.add(nomeLabel);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        spinner.setBounds(x - 40, y + 270, 80, 35);
        panel.add(spinner);

        RoundedButton addButton = new RoundedButton("Adicionar", 20);
        addButton.setBounds(x - 75, y + 330, 150, 40);
        addButton.setBackground(corBotaoLaranjaEscura);
        addButton.setForeground(Color.BLACK);
        addButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        addButton.addActionListener(e -> {
            int quantidade = (int) spinner.getValue();
            carrinho.put(nome, carrinho.getOrDefault(nome, 0) + quantidade);

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
