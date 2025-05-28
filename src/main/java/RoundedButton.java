import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private final int radius;

    // text -> O texto que será exibido no botão.
    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        setOpaque(false); // necessário para desenhar forma personalizada
        setContentAreaFilled(false); // impede Swing de desenhar o fundo padrão
        setFocusPainted(false); // retira o "focus ring"
        setBorderPainted(false); // retira a borda
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha fundo arredondado
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    public void paintBorder(Graphics g) {
        // Não desenha borda (ou desenha se quiseres, com mesma lógica que acima)
    }
}
