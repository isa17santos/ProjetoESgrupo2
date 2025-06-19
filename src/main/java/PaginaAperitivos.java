import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.miginfocom.swing.MigLayout;
import java.util.ArrayList;
import java.util.List;

public class PaginaAperitivos {
    private JPanel mainPanel;
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel barLabel = new JLabel("Bar");
    private JLabel aperitivosLabel = new JLabel("Aperitivos");
    private JLabel cartLabel = new JLabel();

    private BaseDados bd = BaseDados.getInstance();

    private final AppWindow app;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private final Color corFontePreto = Color.decode("#000000");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    public PaginaAperitivos(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {
        mainPanel = new JPanel();
        mainPanel.setBackground(corFundo);
        mainPanel.setLayout(new MigLayout("nogrid, insets 0"));

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));


        // seta andar para atras
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));

        // --------------------- BAR LABEL -----------------------
        barLabel.setHorizontalAlignment(SwingConstants.CENTER);
        barLabel.setForeground(corFundoLabel);
        barLabel.setBackground(corFundo);
        barLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        barLabel.setOpaque(true);
        // --------------------- BAR LABEL -----------------------

        // --------------------- PACKS LABEL -----------------------
        aperitivosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aperitivosLabel.setForeground(corFundoLabel);
        aperitivosLabel.setBackground(corFundo);
        aperitivosLabel.setFont(new Font("Georgia", Font.PLAIN, 70));
        aperitivosLabel.setOpaque(true);
        // --------------------- PACKS LABEL -----------------------

        // Carrinho
        if(bd.getElementosCarrinho().size() > 0){
            ImageIcon carIcon = new ImageIcon(getClass().getResource("/imagens/carrinho_com_compras.png"));
            Image carImg = carIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            cartLabel.setIcon(new ImageIcon(carImg));
        }else {
            ImageIcon carIcon = new ImageIcon(getClass().getResource("/imagens/carrinho_sem_compras.png"));
            Image carImg = carIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            cartLabel.setIcon(new ImageIcon(carImg));
        }

        // Lista de bebidas
        List<Produto> produtos = bd.getProdutosPorTipo(TipoProduto.APERITIVO);

        List<Produto> produtosAtivos = new ArrayList<>();

        for(Produto p: produtos){
            if (p.getEstado().equals(Estado.ATIVO)){
                produtosAtivos.add(p);
            }
        }

        adicionarProdutos(produtosAtivos);

        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(barLabel, "x 590, y 30");
        mainPanel.add(aperitivosLabel, "x 520, y 180");
        mainPanel.add(cartLabel, "x 1100, y 180");

        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarBar();
            }
        });

        // Redirecionar para Carrinho
        cartLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cartLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                app.mostrarCarrinho();
            }
        });
    }

    private void adicionarProdutos(List<Produto> produtos) {
        JPanel produtosPanel = new JPanel(new GridLayout(0, 4, 40, 40));
        produtosPanel.setBackground(corFundo);

        for (Produto produto : produtos) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            itemPanel.setBackground(corFundo);

            // Imagem
            JLabel imagemLabel = new JLabel();
            ImageIcon imgIcon = new ImageIcon(produto.getFoto());
            Image img = imgIcon.getImage().getScaledInstance(190, 240, Image.SCALE_SMOOTH);
            imagemLabel.setIcon(new ImageIcon(img));
            imagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Nome
            JLabel nomeLabel = new JLabel(produto.getNome());
            nomeLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
            nomeLabel.setForeground(corFontePreto);
            nomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            nomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Spinner quantidade
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, produto.getStock(), 1));
            spinner.setMaximumSize(new Dimension(80, 30));
            spinner.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Botão "Adicionar"
            JButton addBtn;
            addBtn = new RoundedButton("Adicionar", 20);
            addBtn.setFont(new Font("Georgia", Font.PLAIN, 18));
            addBtn.setBackground(corFundoLabel);
            addBtn.setForeground(corFontePreto);
            addBtn.setFocusPainted(false);
            addBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            addBtn.addActionListener(e -> {
                int qtd = (int) spinner.getValue();
                ObjetoCarrinho obj = new ObjetoCarrinho(produto, qtd);
                bd.adicionarAoCarrinho(obj);
                bd.gravarDados();
                app.mostrarPaginaAperitivos(); // refresh (opcional)
            });

            // Espaçamento e composição
            itemPanel.add(imagemLabel);
            itemPanel.add(Box.createVerticalStrut(15));
            itemPanel.add(nomeLabel);
            itemPanel.add(Box.createVerticalStrut(15));
            itemPanel.add(spinner);
            itemPanel.add(Box.createVerticalStrut(15));
            itemPanel.add(addBtn);

            produtosPanel.add(itemPanel);
        }

        JScrollPane scrollPane = new JScrollPane(produtosPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(corFundo);

        mainPanel.add(scrollPane, "x 100, y 320, w 1100, h 400");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
