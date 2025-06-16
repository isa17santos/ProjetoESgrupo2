import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class PaginaBebidas {
    private JPanel mainPanel;
    private AppWindow app;
    private Map<String, Integer> carrinho = new HashMap<>();

    public PaginaBebidas(AppWindow app) {
        this.app = app;
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
                app.mostrarBarAdmin(); // Chama o método da AppWindow
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

        // Lista de bebidas
        String[] bebidas = {"Coca Cola", "Sumol Ananás", "Iced tea Limão", "Água"};
        String[] imagemPaths = {
                "/imagens/produtos bar/cocaCola.png",
                "/imagens/produtos bar/lataSumolAnanas.png",
                "/imagens/produtos bar/icedTeaLimão.png",
                "/imagens/produtos bar/agua.png"
        };

        for (int i = 0; i < bebidas.length; i++) {
            adicionarBebida(mainPanel, bebidas[i], imagemPaths[i], 60 + i * 180);
        }

        // Carrinho de compras
        JLabel carrinhoLabel = new JLabel();
        ImageIcon carrinhoIcon = new ImageIcon(getClass().getResource("/imagens/carrinho.png"));
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

    private void adicionarBebida(JPanel panel, String nome, String imagemPath, int x) {
        JLabel bebidaLabel = new JLabel(nome, JLabel.CENTER);
        bebidaLabel.setBounds(x, 310, 160, 30);
        panel.add(bebidaLabel);

        JLabel imagemLabel = new JLabel();
        ImageIcon imgIcon = new ImageIcon(getClass().getResource(imagemPath));
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
