import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {
    private final int arc;

    public RoundedPanel(int arc) {
        super();
        this.arc = arc;
        setOpaque(false); // necessário para permitir fundo arredondado
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getParent() instanceof JPopupMenu) {
            // Não desenhar borda se estiver dentro de popup, o popup já trata disso
            super.paintComponent(g);
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape rounded = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
        g2.setColor(getBackground());
        g2.fill(rounded);

        g2.dispose();
        super.paintComponent(g);
    }
}
