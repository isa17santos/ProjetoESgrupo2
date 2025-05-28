import javax.swing.*;
import java.awt.*;

public class RoundedComboBox<E> extends JComboBox<E> {
    private final int radius;

    // E[] items -> Um array de elementos a incluir na combo box (String[], Filme[], etc.).
    public RoundedComboBox(E[] items, int radius) {
        super(items);
        this.radius = radius;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // deixa Swing desenhar os conteúdos
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.dispose();
    }
}
