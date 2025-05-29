import javax.swing.*;
import java.awt.*;

// FUNCAO QUE CRIA E MANIPULA DAS SETAS DOS JComboBoxes
public class ArrowIcon implements Icon {
    private final int size = 10;
    private final JComboBox<?> comboBox;

    public ArrowIcon(JComboBox<?> comboBox) {
        this.comboBox = comboBox;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.BLACK);
        boolean isPopupVisible = comboBox.isPopupVisible();
        g2.translate(x + size / 2, y + size / 2);
        if (isPopupVisible) g2.rotate(Math.PI);
        g2.translate(-(x + size / 2), -(y + size / 2));
        int[] xPoints = {x, x + size, x + size / 2};
        int[] yPoints = {y, y, y + size};
        g2.fillPolygon(xPoints, yPoints, 3);
        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }
}