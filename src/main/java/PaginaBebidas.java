import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class PaginaBebidas {
    private JPanel mainPanel;
    private JLabel logoLabel;
    private JLabel voltaLabel;
    private JLabel tituloPrincipalLabel;
    private JLabel subtituloLabel;
    private AppWindow app;
    private Map<String, Integer> carrinho = new HashMap<>();

    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private final Color corFontePreto = Color.decode("#000000");
    private final Color corBotaoLaranjaEscura = Color.decode("#FBA720");

    private BaseDados bd;

    public PaginaBebidas(AppWindow app) {
        this.app = app;
        this.bd = BaseDados.getInstance();
        configurarComponentes();
    }

    private void configurarComponentes() {
        mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(1200, 700));
        mainPanel.setBackground(corFundo);

        configurarLogo();
        configurarSetaVoltar();
        configurarTitulos();
        configurarCarrinho();
        configurarListaDeBebidas();
    }

    private void configurarLogo() {
        logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));
        logoLabel.setBounds(20, 10, 200, 200);
        mainPanel.add(logoLabel);
    }

    private void configurarSetaVoltar() {
        voltaLabel = new JLabel();
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
        mainPanel.add(voltaLabel);
    }

    private void configurarTitulos() {
        tituloPrincipalLabel = new JLabel("Bar", SwingConstants.CENTER);
        tituloPrincipalLabel.setForeground(corFundoLabel);
        tituloPrincipalLabel.setBackground(corFundo);
        tituloPrincipalLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        tituloPrincipalLabel.setOpaque(true);
        tituloPrincipalLabel.setBounds(400, 30, 500, 100);
        mainPanel.add(tituloPrincipalLabel);

        subtituloLabel = new JLabel("Bebidas", SwingConstants.CENTER);
        subtituloLabel.setForeground(corFundoLabel);
        subtituloLabel.setFont(new Font("Georgia", Font.PLAIN, 75));
        subtituloLabel.setBounds(400, 200, 500, 80);
        mainPanel.add(subtituloLabel);
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
        mainPanel.add(carrinhoLabel);
    }

    private void configurarListaDeBebidas() {
        java.util.List<Produto> produtos = bd.getProdutosPorTipo(TipoProduto.BEBIDA);
        int numBebidas = Math.min(produtos.size(), 4);
        int espacamento = 300;
        int xBase = 110;

        for (int i = 0; i < numBebidas; i++) {
            Produto produto = produtos.get(i);
            int x = xBase + i * espacamento;
            adicionarBebida(mainPanel, produto.getNome(), produto.getFoto(), x, produto.getIdProduto());
        }
    }

    private void adicionarBebida(JPanel panel, String nome, String imagemPath, int x, int idProduto) {
        JLabel imagemLabel = new JLabel();
        ImageIcon imgIcon = new ImageIcon(imagemPath);
        Image img = imgIcon.getImage().getScaledInstance(81, 221, Image.SCALE_SMOOTH);
        imagemLabel.setIcon(new ImageIcon(img));
        imagemLabel.setBounds(x, 315, 70, 220);
        panel.add(imagemLabel);

        JLabel nomeLabel = new JLabel(nome, JLabel.CENTER);
        nomeLabel.setFont(new Font("Georgia", Font.PLAIN, 25));
        nomeLabel.setBounds(x - 50, 550, 200, 30);
        nomeLabel.setForeground(corFontePreto);
        panel.add(nomeLabel);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        spinner.setBounds(x, 600, 80, 35);
        panel.add(spinner);

        RoundedButton addButton = new RoundedButton("Adicionar", 20);
        addButton.setBounds(x - 20, 660, 150, 40);
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
