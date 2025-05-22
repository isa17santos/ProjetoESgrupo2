import javax.swing.*;
import java.awt.*;

public class CriacaoFinalSala extends JFrame {

    public CriacaoFinalSala() {
        setTitle("Admin - Criação final Salas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(253, 227, 167)); // cor de fundo semelhante

        setLayout(null);

        // Logo do Cinemagic
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(25, 25, 200, 200);
        add(logoLabel);

        // Texto Admin
        JLabel adminLabel = new JLabel("Admin");
        adminLabel.setFont(new Font("Arial", Font.BOLD, 28));
        adminLabel.setForeground(new Color(244, 163, 0));
        adminLabel.setBounds(340, 20, 200, 30);
        add(adminLabel);

        // Texto Sessões - Criação
        JLabel sessoesLabel = new JLabel("Sessões - Criação");
        sessoesLabel.setFont(new Font("Arial", Font.BOLD, 24));
        sessoesLabel.setForeground(new Color(244, 163, 0));
        sessoesLabel.setBounds(270, 60, 300, 30);
        add(sessoesLabel);

        // Mensagem de sucesso
        JLabel sucessoLabel = new JLabel("<html>Sessão criada<br>com sucesso</html>", SwingConstants.CENTER);
        sucessoLabel.setFont(new Font("Arial", Font.BOLD, 26));
        sucessoLabel.setForeground(Color.BLACK);
        sucessoLabel.setBounds(200, 200, 300, 100);
        add(sucessoLabel);

        // Ícone de check
        ImageIcon checkIcon = new ImageIcon(getClass().getResource("/imagens/image-removebg-preview (1).png"));
        JLabel checkLabel = new JLabel(checkIcon);
        checkLabel.setBounds(500, 200, 100, 100);
        add(checkLabel);

        // Botão de voltar com ícone
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/imagens/image-removebg-preview.png"));
        JButton backButton = new JButton(backIcon);
        backButton.setBounds(10, 150, 40, 40);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> voltar());
        add(backButton);
    }

    private void voltar() {
        // Lógica de voltar à tela anterior
        JOptionPane.showMessageDialog(this, "Voltando para a tela anterior...");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CriacaoFinalSala tela = new CriacaoFinalSala();
            tela.setVisible(true);
        });
    }
}
