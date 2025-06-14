import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginaBebidas {
    private JPanel mainPanel;
    private AppWindow app;
    private Map<String, Integer> carrinho = new HashMap<>();

    //adicionado base de dados para tonar dinamico
    private BaseDados bd;

    public PaginaBebidas(AppWindow app) {
        this.app = app;
        this.bd = BaseDados.getInstance();

        mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(800, 800));
        mainPanel.setBackground(new Color(255, 229, 180));

        // Logo
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));
        logoLabel.setBounds(30, 20, 100, 100);
        mainPanel.add(logoLabel);

        // Seta voltar
        JLabel voltarLabel = new JLabel();
        ImageIcon setaIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image setaImg = setaIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        voltarLabel.setIcon(new ImageIcon(setaImg));
        voltarLabel.setBounds(20, 250, 50, 50);
        voltarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltarLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                app.mostrarBar(); // Chama o método da AppWindow
            }
        });
        mainPanel.add(voltarLabel);

        // Título e subtítulo
        JLabel titulo = new JLabel("Bar", JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 40));
        titulo.setBounds(250, 40, 300, 50);
        mainPanel.add(titulo);

        JLabel bebidasLabel = new JLabel("Bebidas", JLabel.CENTER);
        bebidasLabel.setFont(new Font("Serif", Font.BOLD, 30));
        bebidasLabel.setBounds(250, 100, 300, 40);
        mainPanel.add(bebidasLabel);

        // Lista de bebidas - corrigido, estava incompleto e sem a funcionar dinamicamente
        java.util.List<Produto> produtos = bd.getProdutosPorTipo(TipoProduto.BEBIDA);

        int i = 0;
        for(Produto produto : produtos){
            adicionarBebida(mainPanel, produto.getNome(), produto.getFoto(), 60 + i * 180, produto.getIdProduto());
            i++;
        }
        // fim da correção

        // Carrinho de compras
        JLabel carrinhoLabel = new JLabel();
        ImageIcon carrinhoIcon = new ImageIcon(getClass().getResource("/imagens/carrinho_sem_compras.png"));
        Image carrinhoImg = carrinhoIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        carrinhoLabel.setIcon(new ImageIcon(carrinhoImg));
        carrinhoLabel.setBounds(700, 220, 50, 50);
        carrinhoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        carrinhoLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                mostrarCarrinho();
            }
        });
        mainPanel.add(carrinhoLabel);
    }

    //adicionado o campo id produto para identifcar o produto a ser adicionado ao carrinho
    private void adicionarBebida(JPanel panel, String nome, String imagemPath, int x,
                                 int idProduto) {
        JLabel bebidaLabel = new JLabel(nome, JLabel.CENTER);
        bebidaLabel.setBounds(x, 310, 160, 30);
        panel.add(bebidaLabel);

        JLabel imagemLabel = new JLabel();
        ImageIcon imgIcon = new ImageIcon(imagemPath);
        Image img = imgIcon.getImage().getScaledInstance(80, 150, Image.SCALE_SMOOTH);
        imagemLabel.setIcon(new ImageIcon(img));
        imagemLabel.setBounds(x + 30, 160, 100, 150);
        panel.add(imagemLabel);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        spinner.setBounds(x + 40, 350, 50, 30);
        panel.add(spinner);

        JButton addButton = new JButton("Adicionar");
        addButton.setBounds(x + 10, 390, 120, 30);
        addButton.setBackground(new Color(255, 165, 0));
        addButton.setForeground(Color.BLACK);
        addButton.addActionListener(e -> {
            int quantidade = (int) spinner.getValue();
            carrinho.put(nome, carrinho.getOrDefault(nome, 0) + quantidade);

            //adicionar à base de dados - inicio correção
            Produto produto = bd.getProdutobyID(idProduto);
            ObjetoCarrinho objetoCarrinho = new ObjetoCarrinho(produto, quantidade);
            bd.adicionarAoCarrinho(objetoCarrinho);

            System.out.println(bd.getElementosCarrinho());
            bd.gravarDados();
            //fim de correção

            JOptionPane.showMessageDialog(null, nome + " adicionado ao carrinho!");


        });
        panel.add(addButton);
    }

    private void mostrarCarrinho() {
        if (carrinho.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O carrinho está vazio.");
            return;
        }

        StringBuilder mensagem = new StringBuilder("Carrinho:\n");
        for (Map.Entry<String, Integer> entry : carrinho.entrySet()) {
            mensagem.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
